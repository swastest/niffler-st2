package guru.qa.niffler.test.api;

import guru.qa.niffler.api.UserService;
import guru.qa.niffler.jupiter.annotation.ClassPathUser;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

public class RetrofitUpdateUserTest extends BaseRetrofitTest {
   private final UserService userService = getRetrofit(config.getUserDataUrl()).create(UserService.class);
    @ValueSource(strings = {
            "testData/userKzk.json",
            "testData/userUser1.json"
    })
    @ParameterizedTest
    void updateUserData(@ClassPathUser UserJson userJson) throws IOException {

        UserJson updatedUser = userService.updateUserInfo(userJson).execute().body();
        Assertions.assertEquals(userJson.getFirstname(), updatedUser.getFirstname());

    }
}
