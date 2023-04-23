package niffler.jupiter.extension;

import io.qameta.allure.AllureId;
import niffler.jupiter.annotation.User;
import niffler.model.UserJson;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static niffler.jupiter.annotation.User.UserType.*;

public class UserQueueExtension implements
        BeforeEachCallback, // не является частью времени выполнения теста
        AfterTestExecutionCallback,
        ParameterResolver {

    public static ExtensionContext.Namespace USER_EXTENSION_NAMESPACE =
            ExtensionContext.Namespace.create(UserQueueExtension.class);
    private static Queue<UserJson> USERS_WITH_FRIENDS_QUEUE = new ConcurrentLinkedQueue<>();
    private static Queue<UserJson> USERS_INVITATION_SENT_QUEUE = new ConcurrentLinkedQueue<>();
    private static Queue<UserJson> USERS_INVITATION_RECEIVED_QUEUE = new ConcurrentLinkedQueue<>();

    static {
        USERS_WITH_FRIENDS_QUEUE.addAll(List.of(userJson("kzk2", "kzk2"),
                userJson("roro", "roro")));

        USERS_INVITATION_SENT_QUEUE.addAll(List.of(userJson("plov2", "plov2"),
                userJson("user2", "user2")));

        USERS_INVITATION_RECEIVED_QUEUE.addAll(List.of(userJson("plov1", "plov1"),
                userJson("user1", "user1")));
    }

    /*
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        final String testId = getTestId(context);
        Parameter[] testParameters = context.getRequiredTestMethod().getParameters();
        for (Parameter parameter : testParameters) { // пройтись по параметрам и определить аннотацию User+userType
            User desiredUser = parameter.getAnnotation(User.class);
            if (desiredUser != null) {
                User.UserType userType = desiredUser.userType();
                UserJson user = null;
                while (user == null) {
                    switch (userType) {
                        case WITH_FRIEND -> user = USERS_WITH_FRIENDS_QUEUE.poll(); // достать из очереди
                        case INVITATION_SENT -> user = USERS_INVITATION_SENT_QUEUE.poll();
                        case INVITATION_RECEIVED -> user = USERS_INVITATION_RECEIVED_QUEUE.poll();
                    }
                }
                context.getStore(USER_EXTENSION_NAMESPACE).put(testId, Map.of(userType, user));
            }
        }
    }
     */
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        final String testId = getTestId(context);
        List<Parameter> testParameters = Arrays.asList(context.getRequiredTestMethod().getParameters());
        List<User.UserType> desiredUserType = new ArrayList<>();
        for (Parameter parameter : testParameters) {
            if (parameter.isAnnotationPresent(User.class)) {
                desiredUserType.add(parameter.getAnnotation(User.class).userType());
            }
        }

        Map<User.UserType, UserJson> mapUsers = new HashMap<>();
        for (int i = 0; i < desiredUserType.size(); i++) {
            UserJson user = null;
            while (user == null) {
                switch (desiredUserType.get(i)) {
                    case WITH_FRIEND -> user = USERS_WITH_FRIENDS_QUEUE.poll(); // достать из очереди
                    case INVITATION_SENT -> user = USERS_INVITATION_SENT_QUEUE.poll();
                    case INVITATION_RECEIVED -> user = USERS_INVITATION_RECEIVED_QUEUE.poll();
                    default -> throw new IllegalArgumentException("Unknown user type: " + desiredUserType.get(i));
                }
            }
            mapUsers.put(desiredUserType.get(i), user);
        }
        context.getStore(USER_EXTENSION_NAMESPACE).put(testId, mapUsers);
    }


    @SuppressWarnings("uncheked")
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        final String testId = getTestId(context);
        Map<User.UserType, UserJson> freeUser = (Map<User.UserType, UserJson>)
                context.getStore(USER_EXTENSION_NAMESPACE).get(testId);
        User.UserType userType = freeUser.keySet().iterator().next();
        switch (userType) {
            case WITH_FRIEND -> USERS_WITH_FRIENDS_QUEUE.add(freeUser.get(userType));
            case INVITATION_SENT -> USERS_INVITATION_SENT_QUEUE.add(freeUser.get(userType));
            case INVITATION_RECEIVED -> USERS_INVITATION_RECEIVED_QUEUE.add(freeUser.get(userType));
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(User.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(UserJson.class);
    }

    /*
    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        final String testId = getTestId(extensionContext);
        Map<User.UserType, UserJson> user = (Map<User.UserType, UserJson>)
                extensionContext.getStore(USER_EXTENSION_NAMESPACE).get(testId);
        return user.values().iterator().next();
    }
     */
    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        final String testId = getTestId(extensionContext);
        return (UserJson) extensionContext.getStore(USER_EXTENSION_NAMESPACE).get(testId, Map.class)
                .get(parameterContext.getParameter().getDeclaredAnnotation(User.class).userType());
    }

    private String getTestId(ExtensionContext extensionContext) {
        return Objects.requireNonNull(
                extensionContext.getRequiredTestMethod().getAnnotation(AllureId.class)).value();
    }

    private static UserJson userJson(String username, String password) {
        UserJson userJson = new UserJson();
        userJson.setUsername(username);
        userJson.setPassword(password);
        return userJson;
    }
}
