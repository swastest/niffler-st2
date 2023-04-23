package niffler.jupiter.annotation;

import niffler.jupiter.extension.UserQueueExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@ExtendWith(UserQueueExtension.class)
public @interface User {
 UserType userType();
    enum UserType{
        WITH_FRIEND, // kzk2, roro
        INVITATION_SENT, // user2, plov2
        INVITATION_RECEIVED // user1, plov1
    }
}
