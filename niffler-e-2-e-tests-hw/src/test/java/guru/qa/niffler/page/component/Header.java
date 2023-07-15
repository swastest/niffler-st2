package guru.qa.niffler.page.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import guru.qa.niffler.page.FriendsPage;
import guru.qa.niffler.page.MainPage;

import static com.codeborne.selenide.Selenide.$;

public class Header extends BaseComponent<Header> {
    public Header(SelenideElement self) {
        super(self);
    }

    public Header() {
        super($(".header"));
    }

    private SelenideElement headerTitle = self.$(".header__title");
    private SelenideElement friendsButton = self.$("#friends");
    private SelenideElement mainButton = self.$("#main");


    @Override
    public Header checkThatComponentLoad() {
        headerTitle.shouldHave(Condition.text("Niffler. The coin keeper."));
        return this;
    }

    public FriendsPage goToFriendsPage() {
        friendsButton.click();
        return new FriendsPage();
    }

    public MainPage goToMainPage() {
        mainButton.click();
        return new MainPage();
    }
}
