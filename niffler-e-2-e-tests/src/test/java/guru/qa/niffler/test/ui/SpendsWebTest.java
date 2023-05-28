package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.jupiter.annotation.GenerateCategory;
import guru.qa.niffler.jupiter.annotation.GenerateSpend;
import guru.qa.niffler.jupiter.extension.GenerateCategoryExtension;
import guru.qa.niffler.jupiter.extension.GenerateSpendExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.MainFrontPage;
import guru.qa.niffler.page.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith({
        GenerateCategoryExtension.class,
        GenerateSpendExtension.class
})
public class SpendsWebTest extends BaseWebTest {
    @BeforeEach
    void doLogin() {
        Selenide.open(MainFrontPage.URL, MainFrontPage.class).clickOnLoginButton()
                .checkThatPageLoad()
                .fillLoginForm("kzk2", "kzk2");
        new MainPage().checkThatPageLoad();
    }

    @GenerateCategory(
            category = "tst",
            username = "kzk2"
    )
    @GenerateSpend(
            username = "kzk2",
            description = "QA GURU ADVANCED VOL 2",
            currency = CurrencyValues.RUB,
            amount = 52000.00,
            category = "tst"
    )
    @Test
    void spendShouldBeDeletedByActionInTable(SpendJson spend) {
        new MainPage().selectSpendCheckboxInTable(spend.getDescription())
                .clickOnDeleteButton()
                .checkCountEntry(0);
    }
}
