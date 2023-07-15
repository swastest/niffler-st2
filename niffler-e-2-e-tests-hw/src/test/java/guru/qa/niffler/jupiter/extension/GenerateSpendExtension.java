package guru.qa.niffler.jupiter.extension;

import guru.qa.niffler.api.SpendRestClient;
import guru.qa.niffler.jupiter.annotation.GenerateSpend;
import guru.qa.niffler.model.SpendJson;
import org.junit.jupiter.api.extension.*;

import java.util.Date;

public class GenerateSpendExtension implements ParameterResolver, BeforeEachCallback {

    public static ExtensionContext.Namespace NAMESPACE_SPEND = ExtensionContext.Namespace
        .create(GenerateSpendExtension.class);
    private final SpendRestClient spendRestClient = new SpendRestClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        GenerateSpend annotation = context.getRequiredTestMethod()
            .getAnnotation(GenerateSpend.class);

        if (annotation != null) {
            SpendJson spend = new SpendJson();
            spend.setUsername(annotation.username());
            spend.setAmount(annotation.amount());
            spend.setDescription(annotation.description());
            spend.setCategory(annotation.category());
            spend.setSpendDate(new Date());
            spend.setCurrency(annotation.currency());

            SpendJson created = spendRestClient.addSpend(spend);
            context.getStore(NAMESPACE_SPEND).put("spend", created);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(SpendJson.class);
    }

    @Override
    public SpendJson resolveParameter(ParameterContext parameterContext,
        ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE_SPEND).get("spend", SpendJson.class);
    }
}
