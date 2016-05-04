package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader;

import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.config.FrontEndConfig;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import javafx.fxml.FXMLLoader;
import javafx.util.BuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class SpringFxmlLoader{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringFxmlLoader.class);
    @Autowired
    private ApplicationContext applicationContext;

    public Object load(String fxmlFileName) {
        String fullFileName = String.format(
                "%s%s%s",
                FXMLS.LOCATION,
                fxmlFileName,
                FXMLS.EXTENSION);
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = SpringFxmlLoader.class
                    .getResource(fullFileName);
            return loader.load(
                    url,
                    ResourceBundle.getBundle(FrontEndConfig.MESSAGES_LOCATION, Locale.getDefault()),
                    null,
                    applicationContext::getBean);
        } catch (IOException ioException) {
            String logText = String.format(
                    "Exception while loading fxml [%s]",
                    fullFileName
            );
            LOGGER.warn(logText, ioException);
            //Throw the exception as runtime exception so it is not swallowed
            throw new RuntimeException(ioException);
        }
    }
}
