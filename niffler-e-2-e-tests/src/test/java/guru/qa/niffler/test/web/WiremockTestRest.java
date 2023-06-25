package guru.qa.niffler.test.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

import guru.qa.niffler.config.Config;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WiremockTestRest {

    private Config config = Config.getConfig();
    private WireMock wireMock = new WireMock("userdata.niffler.dc", 9000);
    private static ObjectMapper om = new ObjectMapper();

    @Test
    void authMockTest() {
        wireMock.register(WireMock.get("/currentUser?username=roro")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-type", "application/json")
                        .withBody(getMockUserJson())));
        given()
                .when().log().all()
                .param("username", "roro")
                .post(config.getUserdataUrl() + "/currentUser")
                .then().log().all()
                .statusCode(200)
                .body("username", equalTo("Привет из мока"));
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
