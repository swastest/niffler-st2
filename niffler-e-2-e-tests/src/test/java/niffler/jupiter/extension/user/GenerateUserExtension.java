package niffler.jupiter.extension.user;

import com.github.javafaker.Faker;
import dbHelper.dao.UsersDaoCleanJdbcImpl;
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
    private UsersDaoCleanJdbcImpl dao = new UsersDaoCleanJdbcImpl();
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
                dao.createUser((UserEntity) parameter.getParameterizedType());
                userEntities.add(user);
            }
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {

    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }
}
