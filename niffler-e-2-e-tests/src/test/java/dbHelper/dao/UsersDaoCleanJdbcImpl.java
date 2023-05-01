package dbHelper.dao;

import dbHelper.entity.authEntity.UserEntity;
import dbHelper.mangerDb.DataSourceProviderPG;
import dbHelper.mangerDb.ServiceDB;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class UsersDaoCleanJdbcImpl implements UsersDao {
    private static final DataSource dataSource = DataSourceProviderPG.INSTANCE.getDataSource(ServiceDB.NIFFLER_AUTH);


    @Override
    public int createUser(UserEntity user) {
        String insertSql = "INSERT INTO users (username, password, enabled, account_non_expired, " +
                "account_non_locked, credentials_non_expired)" +
                "VALUES(?, ?, ?, ?, ?, ?)";




        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
//            statement.executeUpdate(insertSql, user.getUsername(), user.getPassword(), user.getEnabled(),
//                    user.getAccountNonExpired(), user.getAccountNonLocked(), user.getCredentialsNonExpired())

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        String insertAuthoritiesSql = "INSERT INTO authorities (user_id, authority)" +
                "VALUES '%s', '%s'";
        String userId = null;

        List<String> listAuthorities = user.getAuthorities().stream()
                .map(ae -> ae.getAuthority().name())
                .map(a -> String.format(insertAuthoritiesSql, userId, a)).collect(Collectors.toList());
        return 0;
    }

    @Override
    public int updateUser(UserEntity user) {
        return 0;
    }

    @Override
    public void deleteUser(UserEntity user) {

    }

    @Override
    public UserEntity userInfo(String userName) {
        return null;
    }
}
