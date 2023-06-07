package guru.qa.niffler.jupiter.extension.user;

import com.github.javafaker.Faker;
import guru.qa.niffler.api.AuthRestClient;
import guru.qa.niffler.api.UserDataRestClient;
import guru.qa.niffler.dbHelper.dao.UsersDao;
import guru.qa.niffler.dbHelper.dao.UsersDaoSpringJdbcImpl;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import guru.qa.niffler.jupiter.annotation.GenerateUserApi;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.extension.*;

public class GenerateUserApiExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {
    public static ExtensionContext.Namespace CREATE_USER_API = ExtensionContext.Namespace.create(GenerateUserApiExtension.class);
    private Faker faker = new Faker();
    private final AuthRestClient authRestClient = new AuthRestClient();
    private final UserDataRestClient userDataRestClient = new UserDataRestClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        UserJson userJson = null;
        GenerateUserApi annotation = context.getRequiredTestMethod()
                .getAnnotation(GenerateUserApi.class);
        System.out.println(annotation.username() + "====================================");
        if (annotation.username().length() > 3) {
            userJson = doRegistration(annotation.username(), annotation.password(), annotation.submitPassword());

        }
        if (annotation.username().length() == 0) {
            String username = faker.funnyName().name();
           final String pass = faker.numerify("5-9");
            userJson = doRegistration(username, pass, pass);
        }
        context.getStore(CREATE_USER_API).put(getTestId(context), userJson);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(UserJson.class);
    }

    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(CREATE_USER_API).get(getTestId(extensionContext), UserJson.class);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        UserJson userJson = context.getStore(CREATE_USER_API).get(getTestId(context), UserJson.class);
        if (userJson != null) {
            UsersDao dao = new UsersDaoSpringJdbcImpl();
            UserEntity user = dao.userInfo(userJson.getUsername());
            dao.deleteUser(user);
            System.out.println("Done!");
        }
    }

    private UserJson doRegistration(String username, String password, String submitPassword) {
        authRestClient.getRegistrationToken();
        authRestClient.doRegistration(username, password, submitPassword);
        UserJson currentUserInfo = userDataRestClient.getCurrentUserInfo(username);
        return currentUserInfo;
    }

    private String getTestId(ExtensionContext context) {
        return context.getUniqueId();
    }
}
