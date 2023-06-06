package guru.qa.niffler.condition;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.impl.CollectionSource;
import guru.qa.niffler.model.SpendJson;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SpendCondition {
    public static CollectionCondition spends(SpendJson... expectedSpends) {
        return new CollectionCondition() {
            @Override
            public void fail(CollectionSource collection, @Nullable List<WebElement> elements, @Nullable Exception lastError, long timeoutMs) {

            }

            @Override
            public boolean missingElementSatisfiesCondition() {
                return false;
            }

            @Override
            public boolean test(List<WebElement> webElements) {
                if (webElements.size() != expectedSpends.length) {
                    return false;
                }

                for (int i = 0; i < expectedSpends.length; i++) {
                    WebElement webElement = webElements.get(i);
                    SpendJson expectedSpend = expectedSpends[i];
                    if (!spendRowEqualsSpend(expectedSpend, webElement)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    private static boolean spendRowEqualsSpend(SpendJson expectedSpend, WebElement webElement) {
        return webElement.findElements(By.cssSelector("td")).get(2).getText().equals(expectedSpend.getAmount())
                && webElement.findElements(By.cssSelector("td")).get(3).getText().equals(expectedSpend.getCurrency())
                && webElement.findElements(By.cssSelector("td")).get(4).getText().equals(expectedSpend.getCategory())
                && webElement.findElements(By.cssSelector("td")).get(5).getText().equals(expectedSpend.getDescription());
    }
}
