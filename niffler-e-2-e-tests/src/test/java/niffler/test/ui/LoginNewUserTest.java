package niffler.test.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import dbHelper.dao.UsersDao;
import dbHelper.dao.UsersDaoCleanJdbcImpl;
import dbHelper.entity.authEntity.Authority;
import dbHelper.entity.authEntity.AuthorityEntity;
import dbHelper.entity.authEntity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;

public class LoginNewUserTest {

    UsersDao userDao = new UsersDaoCleanJdbcImpl();
    UserEntity entity;
    @BeforeEach
    void createUserForTest(){
        entity = new UserEntity();
        entity.setPassword("test1");
        entity.setUsername("test1");
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setAuthorities(Arrays.stream(Authority.values()).map(
                a -> {
                    AuthorityEntity ae = new AuthorityEntity();
                    ae.setAuthority(a);
                    return ae;
                }
        ).toList());
        userDao.createUser(entity);
    }
    @Test
    void positiveLogin2() {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(entity.getUsername());
        $("input[name='password']").setValue(entity.getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldHave(Condition.text("Niffler. The coin keeper."));

    }


}
