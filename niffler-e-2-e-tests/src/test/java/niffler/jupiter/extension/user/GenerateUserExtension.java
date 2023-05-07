package niffler.jupiter.extension.user;

import com.github.javafaker.Faker;
import dbHelper.dao.UsersDao;
import dbHelper.dao.UsersDaoCleanJdbcImpl;
import dbHelper.dao.UsersDaoSpringJdbcImpl;
import dbHelper.entity.authEntity.Authority;
import dbHelper.entity.authEntity.AuthorityEntity;
import dbHelper.entity.authEntity.UserEntity;
import niffler.jupiter.annotation.GenerateUser;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GenerateUserExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    public static ExtensionContext.Namespace CREATE_USER = ExtensionContext.Namespace.create(GenerateUserExtension.class);
    private UsersDao dao = new UsersDaoSpringJdbcImpl();
    private Faker faker = new Faker();

    @Override

    public void beforeEach(ExtensionContext context) throws Exception {
        List<Parameter> parameters = Arrays.asList(context.getRequiredTestMethod().getParameters());
        List<UserEntity> userEntities = new ArrayList<>();
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(GenerateUser.class) && parameter.getType().isAssignableFrom(UserEntity.class)) {
                UserEntity user = new UserEntity();
                List<AuthorityEntity> authorities = Stream.of(Authority.read, Authority.write)
                        .map(authVal -> {
                            AuthorityEntity authorityEntity = new AuthorityEntity();
                            authorityEntity.setAuthority(authVal);
                            return authorityEntity;
                        }).toList();

                user.setUsername(faker.name().username());
                user.setPassword("12345");
                user.setEnabled(true);
                user.setAccountNonLocked(true);
                user.setAccountNonExpired(true);
                user.setCredentialsNonExpired(true);
                user.setAuthorities(authorities);
                dao.createUser(user);
                userEntities.add(user);
            }
        }
        context.getStore(CREATE_USER).put(getTestUniqueId(context), userEntities);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        List<UserEntity> list = context.getStore(CREATE_USER).get(getTestUniqueId(context), List.class);
        dao.deleteUser(list.iterator().next());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().isAnnotationPresent(GenerateUser.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(UserEntity.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public UserEntity resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        List<UserEntity> userEntities = extensionContext.getStore(CREATE_USER).get(getTestUniqueId(extensionContext), List.class);
        return userEntities.get(parameterContext.getIndex());
    }

    private String getTestUniqueId(ExtensionContext context) {
        return context.getUniqueId();
    }
}
