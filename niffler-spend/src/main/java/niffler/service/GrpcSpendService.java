package niffler.service;

import com.google.protobuf.Timestamp;
import guru.qa.grpc.niffler.grpc.SpendRequest;
import guru.qa.grpc.niffler.grpc.SpendResponse;
import guru.qa.grpc.niffler.grpc.SpendServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import niffler.data.CategoryEntity;
import niffler.data.SpendEntity;
import niffler.data.repository.CategoryRepository;
import niffler.data.repository.SpendRepository;
import niffler.model.CurrencyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@GrpcService
public class GrpcSpendService extends SpendServiceGrpc.SpendServiceImplBase {

    private final SpendRepository spendRepository;
    private final CategoryRepository categoryRepository;
    private final GrpcCurrencyClient grpcCurrencyClient;

    @Autowired
    public GrpcSpendService(SpendRepository spendRepository, CategoryRepository categoryRepository, GrpcCurrencyClient grpcCurrencyClient) {
        this.spendRepository = spendRepository;
        this.categoryRepository = categoryRepository;
        this.grpcCurrencyClient = grpcCurrencyClient;
    }


    @Override
    public void saveSpendForUser(SpendRequest request, StreamObserver<SpendResponse> responseObserver) {
        SpendEntity spendEntity = new SpendEntity();
        final String username = request.getUsername();
        final String category = request.getCategory();
        Timestamp timestamp = request.getSpendDate();
        Date date = new Date(timestamp.getSeconds() * 1000L);

        spendEntity.setUsername(username);
        spendEntity.setSpendDate(date);
        spendEntity.setCurrency(CurrencyValues.valueOf(request.getCurrency().name()));
        spendEntity.setDescription(request.getDescription());
        spendEntity.setAmount(request.getAmount());

        CategoryEntity categoryEntity = categoryRepository.findAllByUsername(username)
                .stream()
                .filter(c -> c.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Can`t find category by given name: " + category));

        spendEntity.setCategory(categoryEntity);
        spendRepository.save(spendEntity);


        SpendResponse response = SpendResponse.newBuilder()
                .setId(spendEntity.getId().toString())
                .setSpendDate(timestamp)
                .setCategory(request.getCategory())
                .setCurrency(request.getCurrency())
                .setAmount(spendEntity.getAmount())
                .setDescription(spendEntity.getDescription())
                .setUsername(spendEntity.getUsername())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void editSpendForUser(SpendRequest request, StreamObserver<SpendResponse> responseObserver) {
        Timestamp timestamp = request.getSpendDate();
        Date spendDateFromRequest = new Date(timestamp.getSeconds() * 1000L);

        Optional<SpendEntity> spendById = spendRepository.findById(UUID.fromString(request.getId()));
        if (spendById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can`t find spend by given id: " + request.getId());
        } else {
            final String category = request.getCategory();
            CategoryEntity categoryEntity = categoryRepository.findAllByUsername(request.getUsername())
                    .stream()
                    .filter(c -> c.getCategory().equals(category))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Can`t find category by given name: " + category));

            SpendEntity spendEntity = spendById.get();
            spendEntity.setSpendDate(spendDateFromRequest);
            spendEntity.setCategory(categoryEntity);
            spendEntity.setAmount(request.getAmount());
            spendEntity.setDescription(request.getDescription());
            spendRepository.save(spendEntity);

            SpendResponse response = SpendResponse.newBuilder()
                    .setId(spendEntity.getId().toString())
                    .setSpendDate(timestamp)
                    .setCategory(request.getCategory())
                    .setCurrency(request.getCurrency())
                    .setAmount(spendEntity.getAmount())
                    .setDescription(spendEntity.getDescription())
                    .setUsername(spendEntity.getUsername())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }
    }
}
