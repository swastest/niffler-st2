package niffler.jupiter.extension.user;

import io.qameta.allure.AllureId;
import niffler.jupiter.annotation.User;
import niffler.model.UserJson;
import org.junit.jupiter.api.extension.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

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

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        final String testId = getTestId(context);

        List<User.UserType> desiredUserType = Arrays.stream(context.getRequiredTestMethod().getParameters())
                .filter(parameter -> parameter.isAnnotationPresent(User.class))
                .filter(parameter -> parameter.getType().isAssignableFrom(UserJson.class))
                .map(parameter -> parameter.getAnnotation(User.class).userType()).toList();

        List<Map<User.UserType, UserJson>> usersList = new ArrayList<>();

        for (User.UserType userType : desiredUserType) {
            UserJson user = null;
            while (user == null) {
                switch (userType) {
                    case WITH_FRIEND -> user = USERS_WITH_FRIENDS_QUEUE.poll(); // достать из очереди
                    case INVITATION_SENT -> user = USERS_INVITATION_SENT_QUEUE.poll();
                    case INVITATION_RECEIVED -> user = USERS_INVITATION_RECEIVED_QUEUE.poll();
                    default -> throw new IllegalArgumentException("Unknown user type: " + userType);
                }
            }
            usersList.add(Map.of(userType, user));
        }
        context.getStore(USER_EXTENSION_NAMESPACE).put(testId, usersList);
    }

    @SuppressWarnings("uncheked")
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        final String testId = getTestId(context);
        List<Map<User.UserType, UserJson>> freeUsers = context.getStore(USER_EXTENSION_NAMESPACE).get(testId, List.class);
        for (Map<User.UserType, UserJson> freeUser : freeUsers) {
            User.UserType userType = freeUser.keySet().iterator().next();
            switch (userType) {
                case WITH_FRIEND -> USERS_WITH_FRIENDS_QUEUE.add(freeUser.get(userType));
                case INVITATION_SENT -> USERS_INVITATION_SENT_QUEUE.add(freeUser.get(userType));
                case INVITATION_RECEIVED -> USERS_INVITATION_RECEIVED_QUEUE.add(freeUser.get(userType));
            }
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(User.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(UserJson.class);
    }


    @SuppressWarnings("uncheked")
    @Override
    public UserJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        final String testId = getTestId(extensionContext);

        List<Map<User.UserType, UserJson>> usersFromStore = extensionContext.getStore(USER_EXTENSION_NAMESPACE).get(testId, List.class);
        User.UserType userType = parameterContext.getParameter().getDeclaredAnnotation(User.class).userType();

        List<UserJson> listUsers = new ArrayList<>();
        for (Map<User.UserType, UserJson> mapUsers : usersFromStore) {
            listUsers.add(mapUsers.get(userType));
        }

        UserJson user = listUsers.get(parameterContext.getIndex());
        return user;


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
