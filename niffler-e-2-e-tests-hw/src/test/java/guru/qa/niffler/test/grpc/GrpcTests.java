package guru.qa.niffler.test.grpc;

import guru.qa.grpc.niffler.grpc.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class GrpcTests extends BaseGrpcTest {

    @Test
    void getAllCurrencyTest() {
        CurrencyResponse allCurrencies = currencyBlockingStub.getAllCurrencies(EMPTY);

        Assertions.assertEquals(4, allCurrencies.getAllCurrenciesList().size());
        Assertions.assertEquals(CurrencyValues.KZT, allCurrencies.getAllCurrenciesList().get(1).getCurrency());
        Assertions.assertEquals(CurrencyValues.EUR, allCurrencies.getAllCurrenciesList().get(2).getCurrency());
        Assertions.assertEquals(CurrencyValues.USD, allCurrencies.getAllCurrenciesList().get(3).getCurrency());
        Assertions.assertEquals(CurrencyValues.RUB, allCurrencies.getAllCurrenciesList().get(0).getCurrency());
    }

    static Stream<Arguments> calculateRate(){
        return Stream.of(
                Arguments.of(200.00, CurrencyValues.RUB, CurrencyValues.USD, 3.00),
                Arguments.of(3.00, CurrencyValues.USD, CurrencyValues.RUB, 200.00),
                Arguments.of(200.00, CurrencyValues.EUR, CurrencyValues.EUR, 200.00),
                Arguments.of(0, CurrencyValues.RUB, CurrencyValues.USD, 0)
        );
    }
    @MethodSource("calculateRate")
    @ParameterizedTest
    void calculateRateTest(double amt, CurrencyValues from, CurrencyValues to, double expectedResult) {
        CalculateRequest request = CalculateRequest.newBuilder()
                .setAmount(amt)
                .setSpendCurrency(from)
                .setDesiredCurrency(to).build();
        CalculateResponse calculateResponse = currencyBlockingStub.calculateRate(request);
        Assertions.assertEquals(expectedResult, calculateResponse.getCalculatedAmount());
    }

    @Test
    void saveSpendForUserTest() {
        SpendRequest request = SpendRequest.newBuilder().setUsername("roro")
                .setCurrency(CurrencyValues.KZT)
                .setAmount(3000)
                .setDescription("GRPC setDescription")
                .setCategory("пицца").build();
        SpendResponse response = spendBlockingStub.saveSpendForUser(request);

        Assertions.assertNotNull(request.getId());
        Assertions.assertEquals(request.getUsername(), response.getUsername());
        Assertions.assertEquals(request.getAmount(), response.getAmount());
        Assertions.assertEquals(request.getDescription(), response.getDescription());
        Assertions.assertEquals(request.getCurrency(), response.getCurrency());
    }

    @Test
    void editSpendForUserTest() {
        SpendRequest request = SpendRequest.newBuilder().setUsername("roro")
                .setId("1df2b6c1-bf75-4ce4-8faf-370827522520")
                .setCurrency(CurrencyValues.KZT)
                .setAmount(3000)
                .setDescription("New Description")
                .setCategory("пицца").build();

        SpendResponse response = spendBlockingStub.editSpendForUser(request);
        Assertions.assertEquals(request.getDescription(), response.getDescription());
    }
}
