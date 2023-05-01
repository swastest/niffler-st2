package dbHelper.dao;

import dbHelper.entity.authEntity.UserEntity;

public class UsersDaoImpl implements UsersDao {
    @Override
    public int createUser(UserEntity user) {
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

    @Override
    public String getUserId(String userName) {
        return null;
    }
}
