package niffler.jupiter.extension;

import dbHelper.dao.CategoryDaoImpl;
import dbHelper.dao.CategoryDao;
import niffler.jupiter.annotation.GenerateCategory;
import niffler.api.CategoryService;
import niffler.model.CategoryJson;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.extension.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GenerateCategoryExtension implements ParameterResolver, BeforeEachCallback, AfterTestExecutionCallback {

    public static ExtensionContext.Namespace CATEGORY_NAMESPACE = ExtensionContext.Namespace
            .create(GenerateCategoryExtension.class);

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .client(httpClient)
            .baseUrl("http://127.0.0.1:8093")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private final CategoryService categoryService = retrofit.create(CategoryService.class);

    private final CategoryDao manager = new CategoryDaoImpl();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        GenerateCategory annotation = context.getRequiredTestMethod()
                .getAnnotation(GenerateCategory.class);
        if (annotation != null) {
            CategoryJson categoryJson = new CategoryJson();
            categoryJson.setCategory(annotation.category());
            categoryJson.setUsername(annotation.username());

            CategoryJson category = categoryService.addCategory(categoryJson).execute().body();

            context.getStore(CATEGORY_NAMESPACE).put("category", category);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public CategoryJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(CATEGORY_NAMESPACE).get("category", CategoryJson.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        CategoryJson categoryJson = context.getStore(CATEGORY_NAMESPACE).get("category", CategoryJson.class);
        if (categoryJson != null) {
            manager.deleteCategory(categoryJson.getCategory());
        }
    }
}
