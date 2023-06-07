package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import guru.qa.niffler.jupiter.annotation.GenerateUserDataBase;
import guru.qa.niffler.page.MainFrontPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.Test;

public class LoginNewUserTest extends BaseWebTest {
    @Test
    void positiveLogin2(@GenerateUserDataBase UserEntity user) {
        System.out.println(user.toString());
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm(user.getUsername(), "12345");
        new MainPage().checkThatPageLoad();
    }

    @Test
    void negativeLoginTest() {
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm("000302032", "ferfejkljvflv")
                .checkErrorMessage("Неверные учетные данные пользователя");
    }
}
