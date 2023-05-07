package dbHelper.dao;

import dbHelper.entity.authEntity.Authority;
import dbHelper.entity.authEntity.AuthorityEntity;
import dbHelper.entity.authEntity.UserEntity;
import dbHelper.mangerDb.DataSourceProviderPG;
import dbHelper.mangerDb.ServiceDB;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UsersDaoCleanJdbcImpl implements UsersDao {
    private static final DataSource dataSource = DataSourceProviderPG.INSTANCE.getDataSource(ServiceDB.NIFFLER_AUTH);
    private static final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public int createUser(UserEntity user) {

        int executeUpdate;

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            String insertSql = "INSERT INTO users (username, password, enabled, account_non_expired, " +
                    "account_non_locked, credentials_non_expired)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, encoder.encode(user.getPassword()));
                statement.setBoolean(3, user.getEnabled());
                statement.setBoolean(4, user.getAccountNonExpired());
                statement.setBoolean(5, user.getAccountNonLocked());
                statement.setBoolean(6, user.getCredentialsNonExpired());
                executeUpdate = statement.executeUpdate();

                final UUID userId;
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        userId = UUID.fromString(resultSet.getString("id"));
                        user.setId(userId);
                    } else {
                        throw new SQLException("Creating user failed, no ID present");
                    }
                }
            }

            String insertAuthoritiesSql = "INSERT INTO authorities (user_id, authority) VALUES ('%s', '%s')";
            List<String> listAuthorities = user.getAuthorities().stream()
                    .map(ae -> ae.getAuthority().name())
                    .map(a -> String.format(insertAuthoritiesSql, user.getId(), a)).collect(Collectors.toList());

            for (String sql : listAuthorities) {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sql);
                } catch (SQLException e) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    throw new RuntimeException(e);
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return executeUpdate;
    }

    @Override
    public int updateUser(UserEntity user) {
        int executeUpdate;
        String updateSql = "UPDATE users SET username = ?, password = ?, enabled = ?, account_non_expired =?," +
                "account_non_locked =?, credentials_non_expired = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, encoder.encode(user.getPassword()));
            statement.setBoolean(3, user.getEnabled());
            statement.setBoolean(4, user.getAccountNonExpired());
            statement.setBoolean(5, user.getAccountNonLocked());
            statement.setBoolean(6, user.getCredentialsNonExpired());
            executeUpdate = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return executeUpdate;
    }

    @Override
    public void deleteUser(UserEntity user) {
        String userId = getUserId(user.getUsername());
        String deleteUser = "DELETE FROM users WHERE id =?";
        String deleteAuthority = "DELETE FROM authorities WHERE user_id =?";
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement deleteUserStatement = connection.prepareStatement(deleteUser);
                 PreparedStatement deleteAuthStatement = connection.prepareStatement(deleteAuthority)) {
                deleteUserStatement.setString(1, userId);
                deleteUserStatement.executeUpdate();

                deleteAuthStatement.setString(1, userId);
                deleteAuthStatement.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                connection.setAutoCommit(true);
                throw new RuntimeException(e);
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserEntity userInfo(String userName) {
        String sqlSelectUser = "SELECT * FROM users WHERE username = ?";
        String sqlSelectAuthorities = "SELECT * FROM authorities WHERE user_id = ?";
        UserEntity user = new UserEntity();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedUserStatement = connection.prepareStatement(sqlSelectUser);
             PreparedStatement preparedStatementAuthorities = connection.prepareStatement(sqlSelectAuthorities)) {
            preparedUserStatement.setString(1, userName);
            ResultSet rsUser = preparedUserStatement.executeQuery();
            if (rsUser.next()) {
                user.setId((UUID) rsUser.getObject("id"));
                user.setUsername(rsUser.getString("username"));
                user.setPassword(rsUser.getString("password"));
                user.setEnabled(rsUser.getBoolean("enabled"));
                user.setAccountNonExpired(rsUser.getBoolean("account_non_expired"));
                user.setAccountNonLocked(rsUser.getBoolean("account_non_locked"));
                user.setCredentialsNonExpired(rsUser.getBoolean("credentials_non_expired"));

                List<AuthorityEntity> authorityEntitiesList = new ArrayList<>();
                preparedStatementAuthorities.setObject(1, user.getId());
                ResultSet rsAuth = preparedStatementAuthorities.executeQuery();
                while (rsAuth.next()) {
                    AuthorityEntity entity = new AuthorityEntity();
                    entity.setId((UUID) rsAuth.getObject("id"));
                    entity.setAuthority(Authority.valueOf(rsAuth.getString("authority")));
                    authorityEntitiesList.add(entity);
                }

                return user;
            } else {
                throw new IllegalArgumentException("User " + userName + "not fund");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUserId(String userName) {
        String selectUserId = "SELECT * from users where username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectUserId)) {
            statement.setString(1, userName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new IllegalArgumentException("Can`t find user by given username: " + userName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
