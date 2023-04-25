package niffler.jupiter.extension;

import com.fasterxml.jackson.databind.ObjectMapper;
import niffler.model.UserJson;
import niffler.test.LoginTestExArgumentConverter;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.io.IOException;

public class ClassPathUserConverter implements ArgumentConverter {
    private ClassLoader cl = LoginTestExArgumentConverter.class.getClassLoader();
    private static ObjectMapper om = new ObjectMapper();

    @Override
    public UserJson convert(Object source, ParameterContext context) throws ArgumentConversionException {
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
