package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Configuration;
import guru.qa.niffler.jupiter.annotation.WebTest;

@WebTest
public class BaseWebTest {
    static {
        Configuration.browserSize = "1920x1080";
    }
}
