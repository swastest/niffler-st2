
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

