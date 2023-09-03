package guru.qa.niffler.dbHelper.dao;

import guru.qa.niffler.dbHelper.managerDb.ServiceDB;
import guru.qa.niffler.dbHelper.entity.authEntity.UserEntity;
import guru.qa.niffler.dbHelper.jpa.EntityManagerFactoryProviderPG;
import guru.qa.niffler.dbHelper.jpa.JpaTransactionManager;
/*
JPA - 2 базовых интерфейса:
 1. EntityManagerFactory - аналог дата сорса, мех-м кот знает как подключаться к БД и который создает EntityManager
 2. EntityManager - мех-м для получения сущностей из БД, создания итд, интерфейс для непосредственного
  взаимодействия с БД
 */


//внимание!! Наследуемся от класса JpaTransactionManager для управления транзакциями!!!
public class UsersDaoHibernateImpl extends JpaTransactionManager implements UsersDao {

//    private final EntityManagerFactory entityManagerFactory = EntityManagerFactoryProviderPG.INSTANCE.getEmf(ServiceDB.NIFFLER_AUTH);
//    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public UsersDaoHibernateImpl() {
        super(EntityManagerFactoryProviderPG.INSTANCE.getEmf(ServiceDB.NIFFLER_AUTH)
                .createEntityManager()); //создаеи конкретный ентити менеджер(тред локал)
    }

    @Override
    public int createUser(UserEntity user) {
        user.setPassword(encoder.encode(user.getPassword()));
        persist(user);
        return 0;
    }

    @Override
    public int updateUser(UserEntity user) {
//        UserEntity userFind = userInfo(user.getUsername());
//        userFind.setPassword(encoder.encode(user.getPassword()));
//        userFind.setAccountNonExpired(user.getAccountNonExpired());
//        userFind.setAccountNonLocked(user.getAccountNonLocked());
//        userFind.setEnabled(user.getEnabled());
//        userFind.setCredentialsNonExpired(user.getCredentialsNonExpired());
        merge(user);
 //       return merge(user);; - так правильнее, чтобы метод возвращал обновленного юзера!
        //       Но если сейчас так сделаю, то придется много править!!!
         return 0;
    }

    @Override
    public void deleteUser(UserEntity user) {
        transaction(em ->  em.remove(user));
    }

    @Override
    public UserEntity userInfo(String userName) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE username = :username", UserEntity.class)
                .setParameter("username", userName)
                .getSingleResult();
    }

    @Override
    public String getUserId(String userName) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE username = :username", UserEntity.class)
                .setParameter("username", userName)
                .getSingleResult()
                .getId()
                .toString();
    }
}
