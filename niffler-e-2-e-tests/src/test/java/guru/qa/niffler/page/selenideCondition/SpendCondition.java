package guru.qa.niffler.page.selenideCondition;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.impl.CollectionSource;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.utils.Converter;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SpendCondition {
    public static CollectionCondition spends(SpendJson... expectedSpends) {
        return new CollectionCondition() {
            @Override
            public void fail(CollectionSource collection, @Nullable List<WebElement> elements, @Nullable Exception lastError, long timeoutMs) {
                if (elements == null || elements.isEmpty()) {
                    throw new ElementNotFound(collection, toString(), timeoutMs, lastError);
                } else if (elements.size() != expectedSpends.length) {
                    throw new SpendSizeMismatch(collection, Arrays.asList(expectedSpends), elements.stream().map(
                            e -> convertWebElementToSpendJson(e)
                    ).collect(Collectors.toList()), explanation, timeoutMs);
                } else {
                    throw new SpendsMismatch(collection, Arrays.asList(expectedSpends), elements.stream().map(
                            e -> convertWebElementToSpendJson(e)
                    ).collect(Collectors.toList()), explanation, timeoutMs);
                }
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

    private static boolean spendRowEqualsSpend(SpendJson expectedSpend, WebElement row) {
        return row.findElements(By.cssSelector("td")).get(2).getText().equals(expectedSpend.getAmount().toString())
                && row.findElements(By.cssSelector("td")).get(3).getText().equals(expectedSpend.getCurrency().toString())
                && row.findElements(By.cssSelector("td")).get(4).getText().equals(expectedSpend.getCategory())
                && row.findElements(By.cssSelector("td")).get(5).getText().equals(expectedSpend.getDescription())
                && row.findElements(By.cssSelector("td")).get(1).getText().equals(Converter.convertDateToUiTableFormat(expectedSpend.getSpendDate()));
    }

    private static SpendJson convertWebElementToSpendJson(WebElement row) {
        Date dateFromTable = Converter.convertStringDateFromTableToDate(row.findElements(By.cssSelector("td")).get(1).getText());
        SpendJson sj = new SpendJson();
        sj.setSpendDate(dateFromTable);
        sj.setAmount(Double.valueOf(row.findElements(By.cssSelector("td")).get(2).getText()));
        sj.setCategory(row.findElements(By.cssSelector("td")).get(4).getText());
        sj.setDescription(row.findElements(By.cssSelector("td")).get(5).getText());
        sj.setCurrency(CurrencyValues.valueOf(row.findElements(By.cssSelector("td")).get(3).getText()));

        return sj;
    }


}
