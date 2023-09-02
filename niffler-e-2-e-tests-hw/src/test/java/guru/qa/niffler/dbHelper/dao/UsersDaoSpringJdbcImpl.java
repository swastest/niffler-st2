package guru.qa.niffler.dbHelper.dao;

import guru.qa.niffler.dbHelper.entity.authEntity.Authority;
import guru.qa.niffler.dbHelper.entity.authEntity.AuthorityEntity;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import guru.qa.niffler.dbHelper.managerDb.DataSourceProviderPG;
import guru.qa.niffler.dbHelper.managerDb.ServiceDB;
import guru.qa.niffler.dbHelper.mapper.UserEntityRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersDaoSpringJdbcImpl implements UsersDao {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    public UsersDaoSpringJdbcImpl() {
        DataSourceTransactionManager transactionManager =
                new JdbcTransactionManager(DataSourceProviderPG.INSTANCE.getDataSource(ServiceDB.NIFFLER_AUTH));
        this.jdbcTemplate = new JdbcTemplate(transactionManager.getDataSource());
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    @SuppressWarnings("uncheked")
    public int createUser(UserEntity user) {
        return transactionTemplate.execute(status -> {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, enabled, " +
                        "account_non_expired,account_non_locked, credentials_non_expired) " +
                        "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getUsername());
                statement.setString(2, encoder.encode(user.getPassword()));
                statement.setBoolean(3, user.getEnabled());
                statement.setBoolean(4, user.getAccountNonExpired());
                statement.setBoolean(5, user.getAccountNonLocked());
                statement.setBoolean(6, user.getCredentialsNonExpired());
                return statement;
            }, keyHolder);

            final UUID userId = (UUID) keyHolder.getKeys().get("id");
            if (userId == null) {
                throw new IllegalStateException("Unable to retrieve generated id");
            }
            user.setId(userId);
            jdbcTemplate.batchUpdate("INSERT INTO authorities (user_id, authority) VALUES (?, ?)",
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setObject(1, userId);
                            ps.setObject(2, Authority.values()[i].name());
                        }

                        @Override
                        public int getBatchSize() {
                            return Authority.values().length;
                        }
                    });
/*          можно и так, но правильнее через батч
            user.getAuthorities().stream().forEach(a ->    // Это как альтернатива
                    jdbcTemplate.update("INSERT INTO authorities (user_id, authority) VALUES (?, ?)",
                            user.getId(), a.getAuthority().name()));*/
            return 1;
        });
    }

    @Override
    public int updateUser(UserEntity user) {
        return transactionTemplate.execute(status -> {
            jdbcTemplate.update("UPDATE users SET username = ?, password = ?, enabled = ?, account_non_expired =?," +
                            "account_non_locked =?, credentials_non_expired = ? where username = ?",
                    user.getUsername(), user.getPassword(), user.getEnabled(), user.getAccountNonExpired(),
                    user.getAccountNonLocked(), user.getCredentialsNonExpired(), user.getUsername());
//            if (user.getAuthorities() != null) {
//                UUID userId = UUID.fromString(getUserId(user.getUsername()));
//                user.getAuthorities().stream().forEach(a -> jdbcTemplate
//                        .update("UPDATE authorities SET authority = ? WHERE user_id =?", a, userId));
//            }
            return 1;
        });
    }

    @Override
    public void deleteUser(UserEntity user) {
        UUID userId = (user.getId() == null) ? UUID.fromString(getUserId(user.getUsername())) : user.getId();

//        UUID userId;
//        if (user.getId() == null) {
//            userId = UUID.fromString(getUserId(user.getUsername()));
//        } else {
//            userId = user.getId();
//        }
        transactionTemplate.execute(status -> {
            jdbcTemplate.update("DELETE FROM authorities WHERE user_id =?", userId);
            return jdbcTemplate.update("DELETE FROM users WHERE id =?", userId);
        });
    }

    @Override
    public UserEntity userInfo(String userName) {
        UserEntity user = new UserEntity();
        jdbcTemplate.query("SELECT * FROM users WHERE username = ?;",
                UserEntityRowMapper.instance
             /*
              или можно без использования маппера вот так:
              rs -> {
                    user.setId((UUID) rs.getObject("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEnabled(rs.getBoolean("enabled"));
                    user.setAccountNonExpired(rs.getBoolean("account_non_expired"));
                    user.setAccountNonLocked(rs.getBoolean("account_non_locked"));
                    user.setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"));
                }*/, userName);

        List<AuthorityEntity> authorityEntities = jdbcTemplate.query("SELECT * FROM authorities WHERE user_id= ?;",
                rs -> {
                    List<AuthorityEntity> authorities = new ArrayList<>();
                    while (rs.next()) {
                        AuthorityEntity authority = new AuthorityEntity();
                        authority.setAuthority(Authority.valueOf(rs.getString("authority")));
                        authorities.add(authority);
                    }
                    return authorities;
                },
                user.getId());
        user.setAuthorities(authorityEntities);
        return user;
    }

    @Override
    public String getUserId(String userName) {
        return jdbcTemplate.queryForObject("SELECT id FROM users WHERE username = ?",
                String.class, userName);
    }
}
