package guru.qa.niffler.api;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GatewayGraphQLService {

    @POST("/graphql")
    Call<AllCurrenciesGqlResponse> getAllCurrencies(
            @Header("Authorization") String bearerToken,
            @Body JsonNode body
    );

    @POST("/graphql")
    Call<AllCategoriesGqlResponse> getAllCategories(
            @Header("Authorization") String bearerToken,
            @Body JsonNode body
    );

    @POST("/graphql")
    Call<JsonNode> getAllCurrencies(
            @Header("Authorization") String bearerToken,
            @Body JsonNode body
    );

    @POST("/graphql")
    Call<JsonNode> getAllCurrencies(
            @Header("Authorization") String bearerToken,
            @Body JsonNode body
    );
}
