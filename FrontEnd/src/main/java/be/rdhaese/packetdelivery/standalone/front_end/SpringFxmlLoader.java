package be.rdhaese.packetdelivery.standalone.front_end;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFxmlLoader{

    public static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);

    public Object load(String fxmlFileName) {
        try (InputStream fxmlStream = SpringFxmlLoader.class
                .getResource(String.format("/fxml/%s.fxml", fxmlFileName)).openStream()){
            //TODO log entry
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
