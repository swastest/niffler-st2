package guru.qa.niffler.jupiter.extension.user;

import com.github.javafaker.Faker;
import guru.qa.niffler.api.AuthRestClient;
import guru.qa.niffler.api.UserDataRestClient;
import guru.qa.niffler.api.context.CookieContext;
import guru.qa.niffler.dbHelper.dao.UsersDao;
import guru.qa.niffler.dbHelper.dao.UsersDaoHibernateImpl;
import guru.qa.niffler.dbHelper.dao.UsersDaoSpringJdbcImpl;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import guru.qa.niffler.jupiter.annotation.ApiRegistration;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ApiRegistrationExtension implements BeforeEachCallback, AfterTestExecutionCallback {

   public static ExtensionContext.Namespace API_REGISTRATION = ExtensionContext.Namespace.create(ApiRegistrationExtension.class);
    private final AuthRestClient authRestClient = new AuthRestClient();
    private final UserDataRestClient userDataRestClient = new UserDataRestClient();

    private Faker faker = new Faker();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        UserJson userJson;
        ApiRegistration annotation = context.getRequiredTestMethod().getAnnotation(ApiRegistration.class);
        if(annotation!=null){
            userJson =  doRegistration(annotation.login(), annotation.password(), annotation.submitPassword());
            context.getStore(API_REGISTRATION).put(getTestId(context), userJson);
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

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {

        UserJson userJson = context.getStore(API_REGISTRATION).get(getTestId(context), UserJson.class);
        System.out.println("UserJson =======" + userJson.getId());
        if(userJson!=null){
            UsersDao dao = new UsersDaoSpringJdbcImpl();
            UserEntity user = new UserEntity();
            user.setId(userJson.getId());
            user.setUsername(userJson.getUsername());
            System.out.println("UserEntity =======" + user.getId());
            UserEntity user1 = dao.userInfo(user.getUsername());
            dao.deleteUser(user1);
        }

    }

}
