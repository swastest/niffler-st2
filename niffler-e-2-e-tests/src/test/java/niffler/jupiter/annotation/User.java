package niffler.jupiter.annotation;

import niffler.jupiter.extension.user.UserQueueExtension;
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
        WITH_FRIEND,
        INVITATION_SENT,
        INVITATION_RECEIVED
    }
}
