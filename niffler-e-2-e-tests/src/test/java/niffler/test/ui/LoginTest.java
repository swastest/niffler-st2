package niffler.test.ui;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import dbHelper.dao.UsersDao;
import dbHelper.dao.UsersDaoCleanJdbcImpl;
import dbHelper.dao.UsersDaoHibernateImpl;
import dbHelper.dao.UsersDaoSpringJdbcImpl;
import dbHelper.entity.authEntity.Authority;
import dbHelper.entity.authEntity.AuthorityEntity;
import dbHelper.entity.authEntity.UserEntity;
import io.qameta.allure.AllureId;
import niffler.jupiter.annotation.GenerateUser;
import niffler.jupiter.annotation.User;
import niffler.model.UserJson;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;

public class LoginTest extends BaseWebTest {

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
    void createUserJdbc(@GenerateUser() UserEntity user1,
                        @GenerateUser() UserEntity user2) {
        System.out.println(user1.getUsername());
        System.out.println(user1.getPassword());
        System.out.println(user2.getUsername());
        System.out.println(user2.getPassword());
    }

    @Test
    void testDao(){
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

    @AllureId("2")
    @Test
    void positiveLogin2(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson) {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(userJson.getUsername());
        $("input[name='password']").setValue(userJson.getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldHave(Condition.text("Niffler. The coin keeper."));

    }

    @AllureId("3")
    @Test
    void positiveLogin3(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson) {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(userJson.getUsername());
        $("input[name='password']").setValue(userJson.getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldHave(Condition.text("Niffler. The coin keeper."));

    }

    @AllureId("4")
    @Test
    void positiveLogin4(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson) {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(userJson.getUsername());
        $("input[name='password']").setValue(userJson.getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldHave(Condition.text("Niffler. The coin keeper."));
    }

}
