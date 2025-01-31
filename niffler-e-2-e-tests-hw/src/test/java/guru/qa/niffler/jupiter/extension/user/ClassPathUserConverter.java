package guru.qa.niffler.jupiter.extension.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.niffler.model.UserJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.springframework.util.Assert;

import java.io.IOException;

public class ClassPathUserConverter implements ArgumentConverter {
    private ClassLoader cl = ClassPathUserConverter.class.getClassLoader();
    private static ObjectMapper om = new ObjectMapper();

    @Override
    public UserJson convert(Object source, ParameterContext context) throws ArgumentConversionException {
        Assertions.assertTrue(context.getParameter().getType() == UserJson.class);
        if (source instanceof String stringSours) {
            try {
                UserJson user = om.readValue(cl.getResourceAsStream(stringSours), UserJson.class);

                return user;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new ArgumentConversionException("Only string sours support");
        }

    }
}
