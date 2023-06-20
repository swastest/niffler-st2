package guru.qa.niffler.jupiter.extension.user;

import com.codeborne.selenide.Selenide;
import guru.qa.niffler.api.context.CookieContext;
import guru.qa.niffler.api.context.SessionStorageContext;
import guru.qa.niffler.api.AuthRestClient;
import guru.qa.niffler.api.utils.OauthUtils;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.ApiLogin;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ApiLoginExtension implements BeforeEachCallback, AfterTestExecutionCallback {

    private final Config config = Config.getConfig();
   private final  AuthRestClient authRestClient = new AuthRestClient();
    private static final String JSESSIONID = "JSESSIONID";

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ApiLogin annotation = context.getRequiredTestMethod().getAnnotation(ApiLogin.class);
        if (annotation != null) {
            doLogin(annotation.username(), annotation.password());
        }
    }

    private void doLogin(String username, String password) {
        final SessionStorageContext sessionStorageContext = SessionStorageContext.getInstance();
        final CookieContext cookieContext = CookieContext.getInstance();

        final String codeVerifier = OauthUtils.generateCodeVerifier();
        final String codeChallange = OauthUtils.generateCodeChallange(codeVerifier);

        sessionStorageContext.setCodeChallange(codeChallange);
        sessionStorageContext.setCodeVerifier(codeVerifier);
        authRestClient.authorizePreRequest();
        authRestClient.login(username, password);
        final String token = authRestClient.getToken();
        Selenide.open(config.getFrontUrl());
        Selenide.sessionStorage().setItem("id_token", token);
        Selenide.sessionStorage().setItem("codeChallenge", sessionStorageContext.getCodeChallange());
        Selenide.sessionStorage().setItem("codeVerifier", sessionStorageContext.getCodeVerifier());
//        Cookie jSessionId = new Cookie(JSESSIONID, cookieContext.getCookie(JSESSIONID));
//        WebDriverRunner.getWebDriver().manage().addCookie(jSessionId);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        SessionStorageContext.getInstance().release();
        CookieContext.getInstance().release();
    }
}
