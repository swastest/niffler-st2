
# niffler  

Homework #1
* Реализован [GenerateCategoryExtension.java](src%2Ftest%2Fjava%2Fniffler%2Fextensions%2FGenerateCategoryExtension.java) для [SpendsWebTest.java](src%2Ftest%2Fjava%2Fniffler%2Ftest%2FSpendsWebTest.java).
* Добавлен [DataSourcePG.java](src%2Ftest%2Fjava%2FdbHelper%2FDataSourcePG.java) для реализации afterTestExecution

#### Preconditions Setup


| User Name | Password | Categories |
|:----------|:---------|:-----------|
| `kzk2`    | `kzk2`   | `tst`      | 


Homework #2
* Доработан [UserQueueExtension.java](src%2Ftest%2Fjava%2Fniffler%2Fjupiter%2Fextension%2FUserQueueExtension.java) - расширена возможность обработки множества параметров метода с аннотацией User. Проверка в тесте [LoginTest.java](src%2Ftest%2Fjava%2Fniffler%2Ftest%2FLoginTest.java)
* Api test для userData [RetrofitUpdateUserTest.java](src%2Ftest%2Fjava%2Fniffler%2Ftest%2FapiTest%2FRetrofitUpdateUserTest.java)

Homework #3
* Добавлен DAO [UsersDaoCleanJdbcImpl.java](src%2Ftest%2Fjava%2FdbHelper%2Fdao%2FUsersDaoCleanJdbcImpl.java) для полной поддержки CRUD операций юзера Create (создание), Read (чтение), Update (редактирование) и Delete (удаление)
* Добавление и удаление обернуты в транзакцию [UsersDaoCleanJdbcImpl.java](src%2Ftest%2Fjava%2FdbHelper%2Fdao%2FUsersDaoCleanJdbcImpl.java)
* Написан extension для создания нового юзера [GenerateUserExtension.java](src%2Ftest%2Fjava%2Fniffler%2Fjupiter%2Fextension%2Fuser%2FGenerateUserExtension.java), 
аннотация [GenerateUser.java](src%2Ftest%2Fjava%2Fniffler%2Fjupiter%2Fannotation%2FGenerateUser.java), простой тест для проверки работоспособности [LoginTest.java](src%2Ftest%2Fjava%2Fniffler%2Ftest%2Fui%2FLoginTest.java)

Homework #4
* Добавлен метод создания юзера [UsersDaoSpringJdbcImpl.java](src%2Ftest%2Fjava%2FdbHelper%2Fdao%2FUsersDaoSpringJdbcImpl.java)
* Написан extension для создания нового юзера и удаления его после прохождения теста [GenerateUserExtension.java](src%2Ftest%2Fjava%2Fniffler%2Fjupiter%2Fextension%2Fuser%2FGenerateUserExtension.java),
  аннотация [GenerateUser.java](src%2Ftest%2Fjava%2Fniffler%2Fjupiter%2Fannotation%2FGenerateUser.java), простой тест для проверки работоспособности [LoginTest.java](src%2Ftest%2Fjava%2Fniffler%2Ftest%2Fui%2FLoginTest.java)
* Реализован метод update(UserEntity user) для всех трех DAO (hibernate, jdbc, spring-jdbc)

[UsersDaoCleanJdbcImpl.java](src%2Ftest%2Fjava%2FdbHelper%2Fdao%2FUsersDaoCleanJdbcImpl.java)
[UsersDaoHibernateImpl.java](src%2Ftest%2Fjava%2FdbHelper%2Fdao%2FUsersDaoHibernateImpl.java)
[UsersDaoSpringJdbcImpl.java](src%2Ftest%2Fjava%2FdbHelper%2Fdao%2FUsersDaoSpringJdbcImpl.java)


./gradlew :niffler-e-2-e-tests:dependencies

Homework #12
* Реализовать полностью кастомный кондишн spend – написать корректный метод маппинга веб элемента на SpendJson (учитывающий формат дат) <br> 
[SpendCondition.java](src%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Fpage%2FselenideCondition%2FSpendCondition.java) <br>
[SpendsWebTest.java](src%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Ftest%2Fui%2FSpendsWebTest.java)
* Реализовать аннотацию @GenerateUser для тестов – она должна создавать юзера через POST запрос в аус на /register и класть в контекст созданного юзера, полученного из userdata <br>
[GenerateUserApiExtension.java](src%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Fjupiter%2Fextension%2Fuser%2FGenerateUserApiExtension.java)<br>
Тест generateUserApi [UserQueueExampleTests.java](src%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Ftest%2FsoutTests%2FUserQueueExampleTests.java)
* Покрыть юнит-тестами все методы в классе GrpcCurrencyService <br>
[GrpcCurrencyServiceTest.java](..%2Fniffler-currency%2Fsrc%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Fservice%2FGrpcCurrencyServiceTest.java)

Homework #13 <br>
./gradlew :niffler-grpc-common:build
* Реализовать полное покрытие gRPC тестами сервиса niffler-currency <br>
--  gRPC тесты currency и spend (из задания со звездочкой) [GrpcTests.java](src%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Ftest%2Fgrpc%2FGrpcTests.java)
* Реализовать gRPC транспорт для какого-либо другого сервиса niffler - например, userdata или spend.
  При этом сохранив РЕСТ-контроллеры для обратной совместимости. <br>
[niffler-spend.proto](..%2Fniffler-grpc-common%2Fsrc%2Fmain%2Fproto%2Fniffler-spend.proto)
[GrpcSpendService.java](..%2Fniffler-spend%2Fsrc%2Fmain%2Fjava%2Fniffler%2Fservice%2FGrpcSpendService.java)
[SpendServiceGrpc.java](..%2Fniffler-grpc-common%2Fbuild%2Fgenerated%2Fsource%2Fproto%2Fmain%2Fgrpc%2Fguru%2Fqa%2Fgrpc%2Fniffler%2Fgrpc%2FSpendServiceGrpc.java)

Homework #15 <br>
* Для всех сервисов, использующих REST (кроме сервиса gateway), реализовать стабы (моки) их эндпоинтов [HwWireMockTest.java](src%2Ftest%2Fjava%2Fguru%2Fqa%2Fniffler%2Ftest%2FwithWireMockTests%2FHwWireMockTest.java)