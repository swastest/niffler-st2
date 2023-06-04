package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Configuration;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.WebTest;

@WebTest
public class BaseWebTest {
    protected static final Config config = Config.getConfig();
    static {
        Configuration.browserSize = "1920x1080";
    }
}
