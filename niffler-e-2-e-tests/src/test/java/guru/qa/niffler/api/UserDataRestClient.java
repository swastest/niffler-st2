package guru.qa.niffler.api;


import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.Assertions;
import org.springframework.lang.NonNull;

import java.io.IOException;

public class UserDataRestClient extends BaseRestClient {
    public UserDataRestClient() {
        super(config.getUserDataUrl());
    }

    UserDataService userDataService = retrofit.create(UserDataService.class);

    public @NonNull UserJson getCurrentUserInfo(String username) {
        try {
            return userDataService.currentUser(username).execute().body();
        } catch (IOException e) {
            Assertions.fail("Не смогли подключиться к niffler-userData "+e.getMessage());
            return null;
        }
    }

}
