package niffler.test.ui;

import com.codeborne.selenide.Configuration;
import niffler.jupiter.annotation.WebTest;

@WebTest
public class BaseWebTest {
    static {
        Configuration.browserSize = "1920x1080";
    }
}
