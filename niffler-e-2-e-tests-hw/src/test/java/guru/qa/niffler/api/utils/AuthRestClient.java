package guru.qa.niffler.api.utils;

import guru.qa.niffler.api.AuthService;
import guru.qa.niffler.api.BaseRestClient;
import guru.qa.niffler.api.context.CookieContext;
import guru.qa.niffler.api.context.SessionStorageContext;
import guru.qa.niffler.api.interceptor.AddCookiesInterceptor;
import guru.qa.niffler.api.interceptor.RecievedCodeInterceptor;
import guru.qa.niffler.api.interceptor.RecievedCookiesInterceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class AuthRestClient extends BaseRestClient {
    public AuthRestClient() {
        super(config.getAuthUrl(),
                true,
                new RecievedCookiesInterceptor(),
                new AddCookiesInterceptor(),
                new RecievedCodeInterceptor()
        );
    }

    private final AuthService authService = retrofit.create(AuthService.class);

    public void authorizePreRequest() {
        try {
            authService.authorized(
                    "code",
                    "client",
                    "openid",
                    config.getFrontUrl() + "/authorized",
                    SessionStorageContext.getInstance().getCodeChallange(),
                    "S256"
            ).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String username, String password) {
        final CookieContext cookieContext = CookieContext.getInstance();
        try {
            authService.login(
                    cookieContext.getFormattedCookie("JSESSIONID"),
                    cookieContext.getFormattedCookie("XSRF-TOKEN"),
                    cookieContext.getCookie("XSRF-TOKEN"),
                    username, password
            ).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken() {
        SessionStorageContext sessionStorageContext = SessionStorageContext.getInstance();
        try {
            return authService.token(
                    "Basic "
                            + Base64.getEncoder().encodeToString("client:secret".getBytes(StandardCharsets.UTF_8)),
                    "client",
                    config.getFrontUrl() + "/authorized",
                    "authorization_code",
                    sessionStorageContext.getCode(),
                    sessionStorageContext.getCodeVerifier()
            ).execute().body().get("id_token").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
