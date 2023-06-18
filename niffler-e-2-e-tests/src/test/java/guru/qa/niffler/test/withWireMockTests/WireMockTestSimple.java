package guru.qa.niffler.test.withWireMockTests;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.UserJson;
import guru.qa.niffler.page.MainFrontPage;
import guru.qa.niffler.page.MainPage;
import guru.qa.niffler.test.ui.BaseWebTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.UUID;

@WireMockTest(httpPort = 8089)//userData port
public class WireMockTestSimple extends BaseWebTest {
// еще +1 способ настройки мока, позволяющий более тонко настроить конфиг
    // убрав статик данный инстас будет создаваться для каждого теста и мы можем создать несколько моков, которые будем использовать
    // через стандартную аннотацию над классом эта фича не работает
//    @RegisterExtension
//    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
//            .options(
//                    WireMockConfiguration.options().port(8089)
//            )
//            .configureStaticDsl(true).build();
    private static ObjectMapper om = new ObjectMapper();
    @Test
    void registrationAndLoginTest() {
        WireMock.stubFor(WireMock.get("/currentUser?username=roro").willReturn(
                WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json")
                        .withBody(getMockUserJson())
        ));
        Selenide.open(MainFrontPage.URL, MainFrontPage.class)
                .checkThatPageLoad()
                .clickOnLoginButton()
                .fillLoginForm("roro", "roro");
        new MainPage().checkThatPageLoad();
    }

    private String getMockUserJson() {
        UserJson mockUserJson = new UserJson();
        mockUserJson.setUsername("Привет из мока");
        mockUserJson.setId(UUID.randomUUID());
        mockUserJson.setFirstname("Привет из мока");
        mockUserJson.setSurname("Привет из мока");
        mockUserJson.setCurrency(CurrencyValues.EUR);
        mockUserJson.setPhoto(null);
        try {
            String jsonBody = om.writeValueAsString(mockUserJson);
            return jsonBody;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
