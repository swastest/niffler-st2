package guru.qa.niffler.test.soutTests;

import guru.qa.niffler.dbHelper.dao.UsersDao;
import guru.qa.niffler.dbHelper.dao.UsersDaoHibernateImpl;
import guru.qa.niffler.dbHelper.entity.authEntity.Authority;
import guru.qa.niffler.dbHelper.entity.authEntity.AuthorityEntity;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import guru.qa.niffler.jupiter.annotation.GenerateUserApi;
import guru.qa.niffler.jupiter.annotation.GenerateUserDataBase;
import guru.qa.niffler.jupiter.annotation.User;
import guru.qa.niffler.model.UserJson;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class UserQueueExampleTests {

    @AllureId("1")
    @Test
    void positiveLogin1(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson,
                        @User(userType = User.UserType.WITH_FRIEND) UserJson userJson11,
                        @User(userType = User.UserType.INVITATION_RECEIVED) UserJson userJson2,
                        @User(userType = User.UserType.INVITATION_SENT) UserJson userJson3) {
        System.out.println("Test1==========" + userJson.toString());
        System.out.println("Test2==========" + userJson2.toString());
        System.out.println("Test3==========" + userJson3.toString());
        System.out.println("Test3==========" + userJson11.toString());
    }
    @Test
    void createUserJdbc(@GenerateUserDataBase() UserEntity user1,
                        @GenerateUserDataBase() UserEntity user2) {
        System.out.println(user1.getUsername());
        System.out.println(user1.getPassword());
        System.out.println(user2.getUsername());
        System.out.println(user2.getPassword());
    }

    @Test
    void testDao() {
        UsersDao dao = new UsersDaoHibernateImpl();
        UserEntity user = new UserEntity();
        List<AuthorityEntity> authorities = Stream.of(Authority.read, Authority.write)
                .map(authVal -> {
                    AuthorityEntity authorityEntity = new AuthorityEntity();
                    authorityEntity.setAuthority(authVal);
                    authorityEntity.setUser(user);
                    return authorityEntity;
                }).toList();
        user.setPassword("test1");
        user.setUsername("test1");
        user.setAuthorities(authorities);
        user.setEnabled(false);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        dao.updateUser(user);
    }

    @GenerateUserApi(
    )
    @Test
    void generateUserApi(UserJson userJson) {
        System.out.println(userJson);
    }
}
