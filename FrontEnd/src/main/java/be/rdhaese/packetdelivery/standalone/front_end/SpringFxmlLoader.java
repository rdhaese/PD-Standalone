package be.rdhaese.packetdelivery.standalone.front_end;

import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class SpringFxmlLoader{

    private static ApplicationContext applicationContext;

    public Object load(String fxmlFileName) {
        try (InputStream fxmlStream = SpringFxmlLoader.class
                .getResource(String.format("%s%s%s", FXMLS.LOCATION, fxmlFileName, FXMLS.EXTENSION)).openStream()){
            //TODO log entry
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(getApplicationContext()::getBean);
            loader.setResources(getApplicationContext().getBean(ResourceBundle.class));
            return loader.load(fxmlStream);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    public static ApplicationContext getApplicationContext(){
        if (applicationContext == null){
            applicationContext =  new AnnotationConfigApplicationContext(App.class);
        }
        return applicationContext;
    }
}
