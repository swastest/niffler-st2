package guru.qa.niffler.test.ui;


import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.annotation.User;
import guru.qa.niffler.model.UserJson;
import guru.qa.niffler.page.MainFrontPage;
import guru.qa.niffler.page.MainPage;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.Isolated;
import org.junit.jupiter.api.parallel.ResourceLock;

//@Isolated // - изолирует класс, то есть он не будет выполняться параллельно с остальными
//@Execution(ExecutionMode.CONCURRENT)
@Execution(ExecutionMode.SAME_THREAD) //индивидуальная настройка для класса(переопределение пропертей), если есть необходимость
public class LoginTest extends BaseWebTest {
    @ResourceLock("TEST_LOCK") // - такие тесты не будут выполняться параллельно
    @AllureId("2")
    @Test
    void positiveLogin2(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson) {
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm(userJson.getUsername(), userJson.getPassword());
        new MainPage().checkThatPageLoad();
    }

    @ResourceLock("TEST_LOCK") // - такие тесты не будут выполняться параллельно
    @AllureId("3")
    @Test
    void positiveLogin3(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson) {
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm(userJson.getUsername(), userJson.getPassword());
        new MainPage().checkThatPageLoad();
    }

    @AllureId("4")
    @Test
    void positiveLogin4(@User(userType = User.UserType.WITH_FRIEND) UserJson userJson) {
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm(userJson.getUsername(), userJson.getPassword());
        new MainPage().checkThatPageLoad();
    }
}
