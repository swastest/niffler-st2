package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.annotation.ApiLogin;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.Test;

public class ApiToUiTest extends BaseWebTest {


    @ApiLogin(username = "roro",
            password = "roro")
    @Test
    void loginTest() {
        Selenide.open(MainPage.URL, MainPage.class)
                .checkThatPageLoad();
    }
}
