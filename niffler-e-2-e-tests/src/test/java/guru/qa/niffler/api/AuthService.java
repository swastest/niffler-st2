package guru.qa.niffler.api;

import com.fasterxml.jackson.databind.JsonNode;
import retrofit2.Call;
import retrofit2.http.*;

    /*
1 запрос
GET
http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized&code_challenge=ELiOPhpGxfq8hrnpy184VnvZl9QNMQUYls4CdOzqObY&code_challenge_method=S256
Ответ 1 запрос:
302  Set-Cookie: JSESSIONID=72A86F7B537392E3E19BEFCA09F2D3B4; Path=/
возможно 2е куки
Location: http://127.0.0.1:9000/login

2 запрос:
GET http://127.0.0.1:9000/login Cookie: JSESSIONID=72A86F7B537392E3E19BEFCA09F2D3B4
Ответ 2 запрос:
200 Set-Cookie: XSRF-TOKEN=cca666fd-4e7f-4288-bb73-3531e6109edc; Path=/

3 запрос:
POST http://127.0.0.1:9000/login
форм дата _csrf=cca666fd-4e7f-4288-bb73-3531e6109edc
&username=roro
&password=roro
Cookie: JSESSIONID=72A86F7B537392E3E19BEFCA09F2D3B4; XSRF-TOKEN=cca666fd-4e7f-4288-bb73-3531e6109edc
Ответ 3 запрос:
302  Location:
http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized&code_challenge=ELiOPhpGxfq8hrnpy184VnvZl9QNMQUYls4CdOzqObY&code_challenge_method=S256&continue
Set-Cookie: JSESSIONID=D95B74113FD889653D6D484313EA63C9; Path=/
Set-Cookie: XSRF-TOKEN=; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:10 GMT; Path=/

4 запрос:
GET Request URL:
http://127.0.0.1:9000/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://127.0.0.1:3000/authorized&code_challenge=ELiOPhpGxfq8hrnpy184VnvZl9QNMQUYls4CdOzqObY&code_challenge_method=S256&continue
квери параметры: response_type=code
&client_id=client
&scope=openid
&redirect_uri=http://127.0.0.1:3000/authorized
&code_challenge=ELiOPhpGxfq8hrnpy184VnvZl9QNMQUYls4CdOzqObY
&code_challenge_method=S256
&continue
Cookie: JSESSIONID=D95B74113FD889653D6D484313EA63C9
Ответ 4 запроса:
302 Location:
http://127.0.0.1:3000/authorized?code=WMVJrSBrL4JIngNQlhMYD4--qML6l6hSe7h-E4410wEJdbJZDTirDQbqZOXM12icMU3GcwF--jHLnyNN51c7c9a45B_F64U0-WEuZYuasd5NUqMebFLVYYWZV3jOC7Io


5 запрос:
GET Request URL:
http://127.0.0.1:3000/authorized?code=WMVJrSBrL4JIngNQlhMYD4--qML6l6hSe7h-E4410wEJdbJZDTirDQbqZOXM12icMU3GcwF--jHLnyNN51c7c9a45B_F64U0-WEuZYuasd5NUqMebFLVYYWZV3jOC7Io

Cookie: JSESSIONID=D95B74113FD889653D6D484313EA63C9
Ответ запрос5: 200

6 Запрос:
POST http://127.0.0.1:9000/oauth2/token?client_id=client&redirect_uri=http://127.0.0.1:3000/authorized&grant_type=authorization_code&code=WMVJrSBrL4JIngNQlhMYD4--qML6l6hSe7h-E4410wEJdbJZDTirDQbqZOXM12icMU3GcwF--jHLnyNN51c7c9a45B_F64U0-WEuZYuasd5NUqMebFLVYYWZV3jOC7Io&code_verifier=_v1_WgdtkAj7TFEIYh7VT3GOsmoGvzthFXvDhJsIbxM
Authorization: Basic Y2xpZW50OnNlY3JldA==

Ответ 6 запрос:
200
access_token
:
"eyJraWQiOiIxZjg2ZDFmNS1lYjFmLTQ3MTUtYjRmZi01ZDc0NWY4NDNiZmQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJyb3JvIiwiYXVkIjoiY2xpZW50IiwibmJmIjoxNjg1ODAwNDY1LCJzY29wZSI6WyJvcGVuaWQiXSwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo5MDAwIiwiZXhwIjoxNjg1ODM2NDY1LCJpYXQiOjE2ODU4MDA0NjV9.CaFXanNLZApoInNbBnt5YA51PPth27rsPvrtxA30aUKnskmQzcxx7OLTwpWbIRTYPr0VoMxrdtvX3Phbu6mPR2m8YhV-hUl5rb6_q50UDSaz1Ui6_nmkU-N5hlygJzMofm0ehasHfiox0OibgdSd5X9Xcc5cIYzCn53S65KvqYlzaIPaAXS6THRiqo36z2Bo6xusJHZkuJH5FRSGr6I-OKqJtdtlIr15yN_JBuWxYJEllI1PffuZ7dksSaMc9NsUle8dGAwBlc1yfpmB7E99uFtQd6FswKbmfPKJGtRRXL4nmN3E40YikvJOfdFhjbydfV0rvR_XzKquO2YFud6hXw"
expires_in
:
35999
id_token
:
"eyJraWQiOiIxZjg2ZDFmNS1lYjFmLTQ3MTUtYjRmZi01ZDc0NWY4NDNiZmQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJyb3JvIiwiYXVkIjoiY2xpZW50IiwiYXpwIjoiY2xpZW50IiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo5MDAwIiwiZXhwIjoxNjg1ODAyMjY1LCJpYXQiOjE2ODU4MDA0NjV9.Uwg8r9tawwt47nvodTn2U4WHBZirBmxhvAXXIntnk3hLnaXILh3CDKrwIqLkeDdI6XxiP0q0_RbibIn_0JpwTPhK8tKoar34YVPGoxeEjZM4ElpPqvucMAksN6qMauiGX9qjZ5AFp9twlv5eFMAiyZ7Nd5LfFw39nYizk--fsq4sf5oECnuyUbnJ_jnUZYvRWOq746L_apVKbvK-zsAaOm72pvHnGSIhryc7YZzBNGLSZ0d284CKhlwNbVBdFdBaaBfxvFQKm3VX5aJNoyC03L7zqpkAvILcof8b8_YU95ROxswHCx6v30Wy-P5DQZWBMey5orjRev_CdkCKoiiOdw"
refresh_token
:
"k7SPLljPKjnyS2MpfbFvkGqFmqs7dnw9fFLD8UTwVfoKMXC0AgzZhFBwRbQ4dd798uYdGcHOqKLxbyrVOqup9cRAYVrZ02ekT-2jr0zR1UatZeIGupaSLYVqRjJ_S09c"
scope
:
"openid"
token_type
:
"Bearer"

в браузере: session storage
codeChallenge	ELiOPhpGxfq8hrnpy184VnvZl9QNMQUYls4CdOzqObY
id_token	eyJraWQiOiIxZjg2ZDFmNS1lYjFmLTQ3MTUtYjRmZi01ZDc0NWY4NDNiZmQiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJyb3JvIiwiYXVkIjoiY2xpZW50IiwiYXpwIjoiY2xpZW50IiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo5MDAwIiwiZXhwIjoxNjg1ODAyMjY1LCJpYXQiOjE2ODU4MDA0NjV9.Uwg8r9tawwt47nvodTn2U4WHBZirBmxhvAXXIntnk3hLnaXILh3CDKrwIqLkeDdI6XxiP0q0_RbibIn_0JpwTPhK8tKoar34YVPGoxeEjZM4ElpPqvucMAksN6qMauiGX9qjZ5AFp9twlv5eFMAiyZ7Nd5LfFw39nYizk--fsq4sf5oECnuyUbnJ_jnUZYvRWOq746L_apVKbvK-zsAaOm72pvHnGSIhryc7YZzBNGLSZ0d284CKhlwNbVBdFdBaaBfxvFQKm3VX5aJNoyC03L7zqpkAvILcof8b8_YU95ROxswHCx6v30Wy-P5DQZWBMey5orjRev_CdkCKoiiOdw
codeVerifier	_v1_WgdtkAj7TFEIYh7VT3GOsmoGvzthFXvDhJsIbxM

     */

public interface AuthService {
    @GET("/oauth2/authorize")
    Call<Void> authorized(
            @Query("response_type") String responseTypeCode,
            @Query("client_id") String clientId,
            @Query("scope") String scope,
            @Query(value = "redirect_uri", encoded = true) String redirectUri,
            @Query("code_challenge") String codeChallenge,
            @Query("code_challenge_method") String codeChallengeMethod
    );

    @POST("/login")
    @FormUrlEncoded
    Call<Void> login(
            @Header("Cookie") String jsessionId,
            @Header("Cookie") String xsrfToken,
            @Field("_csrf") String csrf,
            @Field("username") String username,
            @Field("password") String password
            );

    @POST("/oauth2/token")
    Call<JsonNode> token(
            @Header("Authorization") String basicAuthToken,
            @Query("client_id") String clientId,
            @Query(value = "redirect_uri", encoded = true) String redirectUri,
            @Query("grant_type") String grantType,
            @Query("code") String code,
            @Query("code_verifier") String codeVerifier
    );
}
