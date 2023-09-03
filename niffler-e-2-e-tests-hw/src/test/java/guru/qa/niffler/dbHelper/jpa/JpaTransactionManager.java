package guru.qa.niffler.dbHelper.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class JpaTransactionManager {
    protected final EntityManager em;

    public JpaTransactionManager(EntityManager em) {
        this.em = em;
    }

/*    в спецификации JPA сказано, что все действие на создание, апдейт или удаление - все они должны быть обернуты
 в транзакцию. И не важно, что мы будем сохранять только в 1 таблицу, например - в любом случае обенуть в транзакцию!
 ИНАЧЕ НИЧЕ РАБОТАТЬ НЕ БУДЕТ
* */
    protected <T>  void persist(T entity) {
        transaction(em -> em.persist(entity));
    }

    protected <T>  void remove(T entity) {
        transaction(em -> em.persist(entity));
    }
    protected <T> T merge(T entity) {
        return transactionWithResult(em -> em.merge(entity));
    }

    protected void transaction(Consumer<EntityManager> action) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            action.accept(em);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    protected <T> T transactionWithResult(Function<EntityManager, T> action) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            T result = action.apply(em);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
