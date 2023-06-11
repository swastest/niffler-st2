package guru.qa.niffler.test.grpc;

import com.google.protobuf.Empty;
import guru.qa.grpc.niffler.grpc.NifflerCurrencyProto;
import guru.qa.grpc.niffler.grpc.NifflerCurrencyServiceGrpc;
import guru.qa.grpc.niffler.grpc.NifflerSpendProto;
import guru.qa.grpc.niffler.grpc.SpendServiceGrpc;
import guru.qa.niffler.config.Config;
import guru.qa.niffler.jupiter.annotation.GrpcTest;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.qameta.allure.grpc.AllureGrpc;

@GrpcTest
abstract class BaseGrpcTest {
    static Config config = Config.getConfig();
    private static Channel channelCurrency;
    private static Channel channelSpend;

    protected static final Empty EMPTY = Empty.getDefaultInstance();

    static {
        channelCurrency = ManagedChannelBuilder
                .forAddress(config.getCurrencyGrpcUrl(), config.getCurrencyGrpcPort())
                .intercept(new AllureGrpc())
                .usePlaintext() // без шифрования
                .build();
        channelSpend = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .intercept(new AllureGrpc())
                .usePlaintext()
                .build();
    }

    protected final NifflerCurrencyServiceGrpc.NifflerCurrencyServiceBlockingStub currencyBlockingStub =
            NifflerCurrencyServiceGrpc.newBlockingStub(channelCurrency);

    protected final SpendServiceGrpc.SpendServiceBlockingStub spendBlockingStub =
            SpendServiceGrpc.newBlockingStub(channelSpend);
}
