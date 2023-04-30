package niffler.test.apiTest;

import niffler.api.UserService;
import niffler.jupiter.annotation.ClassPathUser;
import niffler.model.UserJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

public class RetrofitUpdateUserTest extends BaseRetrofitTest {
   private static UserService userService = getRetrofit("http://127.0.0.1:8089").create(UserService.class);
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
