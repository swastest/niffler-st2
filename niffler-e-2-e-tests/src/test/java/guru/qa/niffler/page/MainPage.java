package guru.qa.niffler.page;

import com.codeborne.selenide.*;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.component.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static guru.qa.niffler.page.selenideCondition.SpendCondition.spends;

public class MainPage extends BasePage<MainPage> {
    private static final Config config = Config.getConfig();

    public static final String URL = config.getFrontUrl() + "/main";
    private final Header header = new Header();
    private ElementsCollection spendTable = $(".spendings-table tbody").$$("tr");
    private SelenideElement deleteButton = $$(".button_type_small").find(text("Delete selected"));
    @Override
    public MainPage checkThatPageLoad() {
        $(byText("History of spendings")).shouldBe(Condition.visible);
        return this;
    }

    public Header getHeader() {
        return header;
    }

    private SelenideElement getCellFromTable(int rowNumber, int cellNumber) {
       return spendTable.get(rowNumber).$$("td").get(cellNumber);
    }

    public MainPage selectSpendCheckboxInTable(String spendName) {
        spendTable.find(text(spendName)).find("td").scrollTo().click();
        return this;
    }

    public MainPage clickOnDeleteButton() {
        deleteButton.click();
        return this;
    }

    public MainPage checkCountEntry(int size) {
        spendTable.shouldHave(CollectionCondition.size(size));
        return this;
    }

    public MainPage checkSpendContentInTable(SpendJson spendJson) {
        spendTable.shouldHave(spends(spendJson));
        return this;
    }
}
