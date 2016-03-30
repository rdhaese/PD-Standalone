package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader;

import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

@Component
public class SpringFxmlLoader{

    @Autowired
    private ApplicationContext applicationContext;

    public Object load(String fxmlFileName) {
        try (InputStream fxmlStream = SpringFxmlLoader.class
                .getResource(String.format("%s%s%s", FXMLS.LOCATION, fxmlFileName, FXMLS.EXTENSION)).openStream()){
            //TODO log entry
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(applicationContext::getBean);
            loader.setResources(applicationContext.getBean(ResourceBundle.class));
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
