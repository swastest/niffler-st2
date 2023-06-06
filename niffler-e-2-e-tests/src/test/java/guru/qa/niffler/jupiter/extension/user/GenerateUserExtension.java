package guru.qa.niffler.jupiter.extension.user;

import com.github.javafaker.Faker;
import guru.qa.niffler.dbHelper.dao.UsersDao;
import guru.qa.niffler.dbHelper.dao.UsersDaoSpringJdbcImpl;
import guru.qa.niffler.jupiter.annotation.GenerateUser;
import guru.qa.niffler.dbHelper.dao.UsersDaoHibernateImpl;
import guru.qa.niffler.dbHelper.entity.authEntity.Authority;
import guru.qa.niffler.dbHelper.entity.authEntity.AuthorityEntity;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GenerateUserExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    public static ExtensionContext.Namespace CREATE_USER = ExtensionContext.Namespace.create(GenerateUserExtension.class);
    private Faker faker = new Faker();

    @Override

    public void beforeEach(ExtensionContext context) throws Exception {
        UsersDao dao = new UsersDaoHibernateImpl();

        List<UserEntity> userEntities = new ArrayList<>();
        List<Parameter> parameters = Arrays.asList(context.getRequiredTestMethod().getParameters()).stream()
                .filter(parameter -> parameter.isAnnotationPresent(GenerateUser.class))
                .filter(parameter -> parameter.getType().isAssignableFrom(UserEntity.class)).toList();
        for (Parameter parameter : parameters) {
                UserEntity user = new UserEntity();
                List<AuthorityEntity> authorities = Stream.of(Authority.values())
                        .map(authVal -> {
                            AuthorityEntity authorityEntity = new AuthorityEntity();
                            authorityEntity.setAuthority(authVal);
                            authorityEntity.setUser(user);
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
        context.getStore(CREATE_USER).put(getTestUniqueId(context), userEntities);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        UsersDao dao = new UsersDaoHibernateImpl();
        List<UserEntity> list = context.getStore(CREATE_USER).get(getTestUniqueId(context), List.class);
        for(UserEntity user : list){
            dao.deleteUser(user);
        }
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
