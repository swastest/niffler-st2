package guru.qa.niffler.jupiter.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

public interface SuiteExtension extends BeforeAllCallback {

    default void beforeSuite() {
    }

    default void afterSuite() {
    }

    @Override
    default void beforeAll(ExtensionContext context) throws Exception {
        context.getRoot().getStore(Namespace.GLOBAL)
                .getOrComputeIfAbsent(
                        SuiteExtension.class, //this.getClass  можно указать и тогда- реальный класс интерфеса, то что будет имплементить
                        k -> {
                            beforeSuite();
                            return (ExtensionContext.Store.CloseableResource) new ExtensionContext.Store.CloseableResource() {
                                @Override
                                public void close() throws Throwable {
                                    SuiteExtension.this.afterSuite();
                                }
                            };
                        });
    }
}
