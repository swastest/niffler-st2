package guru.qa.niffler.dbHelper.entity.authEntity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
/*
как сгенерировать через идею(платную) - актуально только при использовании JPA:
1. создать и прописать все настройки  в persistence.xml  (resources/META-INF/persistence.xml)
2. View -> ToolWindows-> Persistence (открывается подОкошко с нашими модулями)
3. Кликнуть по нужному модулю, где хотим создать ентити и выбрать в открывшемся меню
Generate Persistence Mapping -> By Database Schema (открылось окно Import Database Schema)
4. Работа с окном:
- В поле Choice Data Source выбрать нужный датасорс
- Entity Suffix = Entity
- выбираем наши таблицы и поля для генерации
- проверяем галочку (чтобы стояла) Generate JPA Annotation
- поставить галочку по желанию Generate Column Properties
- нажать на плюсик, над таблицей с таблицами из бд - открывается подокно
        -*** в Подокне Add Relationship устанавливаем связи
        (указываем поле в главной таблице и в таргет таблице, поле фореген кии)
5. Не забыть установить название пакета еще

Но конечно генерится это все не идеально и нужно править руками - лучше знать этот материал и самой составлять ))))
 */

@Entity
@Table(name = "authorities")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityEntity that = (AuthorityEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(authority, that.authority) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, user);
    }
}
