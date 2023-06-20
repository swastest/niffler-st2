package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.niffler.jupiter.annotation.ClassPathUser;
import guru.qa.niffler.model.UserJson;
import guru.qa.niffler.page.MainFrontPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

public class LoginTestExArgumentConverter extends BaseWebTest {
    @ValueSource(strings = {
            "testData/userKzk.json",
            "testData/userUser1.json"})
    @ParameterizedTest
    void positiveLogin5(@ClassPathUser UserJson user) throws IOException {
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm(user.getUsername(), user.getPassword());
        new MainPage().checkThatPageLoad();
    }


    /// заменили ArgumentConverter (пример выше)
    private static ObjectMapper om = new ObjectMapper();
    private ClassLoader cl = LoginTestExArgumentConverter.class.getClassLoader();

    @ValueSource(strings = {
            "testData/userKzk.json",
            "testData/userUser1.json"})
    @ParameterizedTest
    void positiveLogin6(String pathToUser) throws IOException {
        UserJson user = om.readValue(cl.getResourceAsStream(pathToUser), UserJson.class);
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm(user.getUsername(), user.getPassword());
        new MainPage().checkThatPageLoad();

    }
}
