package guru.qa.niffler.dbHelper.dao;

import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UsersDao {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    int createUser(UserEntity user);
    int updateUser(UserEntity user);
    void deleteUser(UserEntity user);
    UserEntity userInfo(String userName);
    String getUserId(String userName);
}
