package niffler.test.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.ObjectMapper;
import niffler.jupiter.annotation.ClassPathUser;
import niffler.model.UserJson;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;

public class LoginTestExArgumentConverter extends BaseWebTest {
    @ValueSource(strings = {
            "testData/userKzk.json",
            "testData/userUser1.json"})
    @ParameterizedTest
    void positiveLogin5(@ClassPathUser UserJson user) throws IOException {
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(user.getUsername());
        $("input[name='password']").setValue(user.getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldHave(Condition.text("Niffler. The coin keeper."));

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
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue(user.getUsername());
        $("input[name='password']").setValue(user.getPassword());
        $("button[type='submit']").click();
        $(".header__title").shouldHave(Condition.text("Niffler. The coin keeper."));

    }
}
