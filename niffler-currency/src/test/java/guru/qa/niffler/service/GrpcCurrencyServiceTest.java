package guru.qa.niffler.service;

import com.google.protobuf.Empty;
import guru.qa.grpc.niffler.grpc.CalculateRequest;
import guru.qa.grpc.niffler.grpc.CalculateResponse;
import guru.qa.grpc.niffler.grpc.Currency;
import guru.qa.grpc.niffler.grpc.CurrencyResponse;
import guru.qa.niffler.data.CurrencyEntity;
import guru.qa.niffler.data.CurrencyValues;
import guru.qa.niffler.data.repository.CurrencyRepository;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GrpcCurrencyServiceTest {
    GrpcCurrencyService grpcCurrencyService;
    List<CurrencyEntity> testCurrencyList;

    @BeforeEach
    void setUp(@Mock CurrencyRepository currencyRepository) {
        CurrencyEntity currencyEntityRub = new CurrencyEntity();
        currencyEntityRub.setCurrency(CurrencyValues.RUB);
        currencyEntityRub.setCurrencyRate(23.3);
        CurrencyEntity currencyEntityEuro = new CurrencyEntity();
        currencyEntityEuro.setCurrency(CurrencyValues.EUR);
        currencyEntityEuro.setCurrencyRate(3.2);
        CurrencyEntity currencyEntityKzt = new CurrencyEntity();
        currencyEntityKzt.setCurrency(CurrencyValues.KZT);
        currencyEntityKzt.setCurrencyRate(4.4);
        CurrencyEntity currencyEntityUSd = new CurrencyEntity();
        currencyEntityUSd.setCurrency(CurrencyValues.USD);
        currencyEntityUSd.setCurrencyRate(45.3);

        testCurrencyList = List.of(currencyEntityEuro, currencyEntityRub, currencyEntityKzt, currencyEntityUSd);

        Mockito.lenient().when(currencyRepository.findAll()).thenReturn(testCurrencyList);

        grpcCurrencyService = new GrpcCurrencyService(currencyRepository);
    }

    @Test
    void getAllCurrencies() {
        StreamObserver<CurrencyResponse> responseStreamObserver = mock(StreamObserver.class);
        grpcCurrencyService.getAllCurrencies(Empty.getDefaultInstance(), responseStreamObserver);

        List<Currency> expectedCurrencies = testCurrencyList.stream()
                .map(e -> Currency.newBuilder()
                        .setCurrency(guru.qa.grpc.niffler.grpc.CurrencyValues.valueOf(e.getCurrency().name()))
                        .setCurrencyRate(e.getCurrencyRate())
                        .build())
                .toList();

        CurrencyResponse expectedResponse = CurrencyResponse.newBuilder()
                .addAllAllCurrencies(expectedCurrencies)
                .build();

        verify(responseStreamObserver).onNext(refEq(expectedResponse));
        verify(responseStreamObserver).onCompleted();
    }

    @Test
    void calculateRate() {
        StreamObserver<CalculateResponse> responseObserver = mock(StreamObserver.class);
        CalculateRequest calculateRequest = CalculateRequest.newBuilder()
                .setSpendCurrency(guru.qa.grpc.niffler.grpc.CurrencyValues.EUR)
                .setDesiredCurrency(guru.qa.grpc.niffler.grpc.CurrencyValues.RUB)
                .setAmount(400.35).build();
        grpcCurrencyService.calculateRate(calculateRequest, responseObserver);

        verify(responseObserver).onNext(refEq(
                CalculateResponse.newBuilder()
                        .setCalculatedAmount(54.98)
                        .build()
        ));
        verify(responseObserver).onCompleted();
    }

    static Stream<Arguments> convertSpendTo() {
        return Stream.of(
                Arguments.of(150, guru.qa.grpc.niffler.grpc.CurrencyValues.RUB,
                        guru.qa.grpc.niffler.grpc.CurrencyValues.EUR, new BigDecimal("1092.19")),
                Arguments.of(400, guru.qa.grpc.niffler.grpc.CurrencyValues.RUB,
                        guru.qa.grpc.niffler.grpc.CurrencyValues.USD, new BigDecimal("205.74")),
                Arguments.of(400, guru.qa.grpc.niffler.grpc.CurrencyValues.KZT,
                        guru.qa.grpc.niffler.grpc.CurrencyValues.KZT, new BigDecimal("400.00")),
                Arguments.of(0.00, guru.qa.grpc.niffler.grpc.CurrencyValues.KZT,
                        guru.qa.grpc.niffler.grpc.CurrencyValues.KZT, new BigDecimal("00.00"))
        );
    }

    @MethodSource("convertSpendTo")
    @ParameterizedTest()
    void convertSpendTo(double spend,
                        guru.qa.grpc.niffler.grpc.CurrencyValues spendCurrency,
                        guru.qa.grpc.niffler.grpc.CurrencyValues desiredCurrency,
                        BigDecimal expectedResult) {
        BigDecimal result = grpcCurrencyService.convertSpendTo(spend, spendCurrency, desiredCurrency, testCurrencyList);
        Assertions.assertEquals(expectedResult, result);
    }

    static Stream<Arguments> courseForCurrency() {
        return Stream.of(
                Arguments.of(guru.qa.grpc.niffler.grpc.CurrencyValues.EUR, new BigDecimal("3.2")),
                Arguments.of(guru.qa.grpc.niffler.grpc.CurrencyValues.USD, new BigDecimal("45.3")),
                Arguments.of(guru.qa.grpc.niffler.grpc.CurrencyValues.RUB, new BigDecimal("23.3")),
                Arguments.of(guru.qa.grpc.niffler.grpc.CurrencyValues.KZT, new BigDecimal("4.4"))
        );
    }

    @MethodSource("courseForCurrency")
    @ParameterizedTest
    void courseForCurrency(guru.qa.grpc.niffler.grpc.CurrencyValues currency, BigDecimal expectedResult) {
        BigDecimal result = grpcCurrencyService.courseForCurrency(currency, testCurrencyList);
        Assertions.assertEquals(expectedResult, result);

    }
}