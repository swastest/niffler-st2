package guru.qa.niffler.dbHelper.jpa;

import guru.qa.niffler.dbHelper.managerDb.ServiceDB;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EntityManagerFactoryProviderPG {
    INSTANCE;
    private Map<ServiceDB, EntityManagerFactory> emfStore = new ConcurrentHashMap<>();

    public EntityManagerFactory getEmf(ServiceDB serviceDB) {
        return emfStore.computeIfAbsent(serviceDB, key -> {
            Map<String, Object> properties = new HashMap<>();
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            properties.put("hibernate.connection.username", "postgres");
            properties.put("hibernate.connection.password", "secret");
            properties.put("hibernate.connection.url", key.getJdbcUrl());

            return new ThredLocalEntityManagerFactory(Persistence.createEntityManagerFactory(
                    //тут внимательно!! стрингой передаем нейм из persistence.xml (persistence-unit name="niffler-persistence-unit-name")
                    "niffler-persistence-unit-name", properties));
        });
    }
}
