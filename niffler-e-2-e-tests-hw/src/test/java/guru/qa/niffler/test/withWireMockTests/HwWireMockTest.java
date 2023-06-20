package guru.qa.niffler.test.withWireMockTests;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.page.MainFrontPage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.test.ui.BaseWebTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HwWireMockTest extends BaseWebTest {
    private static ObjectMapper om = new ObjectMapper();

    @RegisterExtension
    WireMockExtension wireMockSpendExtension = WireMockExtension.newInstance()
            .options(
                    WireMockConfiguration.options().port(8093)
            )
            .configureStaticDsl(true).build();

    @RegisterExtension
    WireMockExtension wireMockAuthExtension = WireMockExtension.newInstance()
            .options(
                    WireMockConfiguration.options().port(9000)
            )
            .configureStaticDsl(true).build();

    @Disabled
    @Test
    void spendMockTest() {
        WireMock.stubFor(WireMock.get("/spends?username=roro").willReturn(
                WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json")
                        .withBody(getMockSpendJson())
        ));
        Selenide.open(MainFrontPage.URL, MainFrontPage.class)
                .checkThatPageLoad()
                .clickOnLoginButton()
                .fillLoginForm("roro", "roro");
        new MainPage().checkThatPageLoad()
                .checkCountEntry(1);
    }

    @Test
    void authMockTest() {
        WireMock.stubFor(WireMock.post("/register").willReturn(
                WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json")
                        .withBody(getMockSpendJson())
        ));
        given()
                .when().log().all()
                .post(config.getAuthUrl()+"/register")
                .then().log().all()
                .statusCode(200)
                .body("username[0]", equalTo("Привет из мока"));
    }

    private static String getMockSpendJson() {
        SpendJson mockSpendJson = new SpendJson();
        mockSpendJson.setUsername("Привет из мока");
        mockSpendJson.setId(UUID.randomUUID());
        mockSpendJson.setSpendDate(new Date());
        mockSpendJson.setCategory("Привет из мока");
        mockSpendJson.setCurrency(CurrencyValues.EUR);
        mockSpendJson.setAmount(66665.55);
        mockSpendJson.setDescription("Из мока привет");

        List<SpendJson> jsonList = new ArrayList<>();
        jsonList.add(mockSpendJson);
        try {
            String jsonBody = om.writeValueAsString(jsonList);
            return jsonBody;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
