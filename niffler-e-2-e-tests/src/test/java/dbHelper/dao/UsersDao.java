package dbHelper.dao;

import dbHelper.entity.authEntity.UserEntity;

public interface UsersDao {
    int createUser(UserEntity user);
    int updateUser(UserEntity user);
    void deleteUser(UserEntity user);
    UserEntity userInfo(String userName);
    String getUserId(String userName);
}
