package niffler.test.ui;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import niffler.jupiter.annotation.GenerateCategory;
import niffler.jupiter.annotation.GenerateSpend;
import niffler.jupiter.extension.GenerateCategoryExtension;
import niffler.jupiter.extension.GenerateSpendExtension;
import niffler.model.CurrencyValues;
import niffler.model.SpendJson;
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
        Selenide.open("http://127.0.0.1:3000/main");
        $("a[href*='redirect']").click();
        $("input[name='username']").setValue("kzk2");
        $("input[name='password']").setValue("kzk2");
        $("button[type='submit']").click();
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
        $(".spendings-table tbody").$$("tr")
                .find(text(spend.getDescription()))
                .$$("td").first()
                .scrollTo()
                .click();

        $$(".button_type_small").find(text("Delete selected"))
                .click();

        $(".spendings-table tbody")
                .$$("tr")
                .shouldHave(CollectionCondition.size(0));
    }
}
