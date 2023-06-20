package guru.qa.niffler.api;

import com.fasterxml.jackson.databind.JsonNode;
import guru.qa.niffler.model.UserJson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserDataService {
    /*
        http://127.0.0.1:8089/currentUser?username=popo
        200
     */

    @GET("/currentUser")
    Call<UserJson> currentUser (
            @Query("username") String username
    );
}
