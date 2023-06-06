package guru.qa.niffler.test.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.jupiter.annotation.ApiLogin;
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

import static com.codeborne.selenide.Selenide.$;


@ExtendWith({
        GenerateCategoryExtension.class,
        GenerateSpendExtension.class
})
public class SpendsWebTest extends BaseWebTest {

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
    @ApiLogin(username = "kzk2",
            password = "kzk2")
    @Test
    void spendTableEqualsToGiven(SpendJson spend) {
        SelenideElement spendRow = $(".spendings-table tbody").$$("tr").find(Condition.text(spend.getDescription()));

        Selenide.open(MainPage.URL, MainPage.class).selectSpendCheckboxInTable(spend.getDescription())
                .clickOnDeleteButton()
                .checkCountEntry(0);
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
    @ApiLogin(username = "kzk2",
            password = "kzk2")
    @Test
    void spendShouldBeDeletedByActionInTable(SpendJson spend) {
        Selenide.open(MainPage.URL, MainPage.class).selectSpendCheckboxInTable(spend.getDescription())
                .clickOnDeleteButton()
                .checkCountEntry(0);
    }
}
