package be.rdhaese.packetdelivery.standalone.front_end;

import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.service.ContactInformationService;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication( scanBasePackages = "be.rdhaese.packetdelivery.standalone")
@EnableAspectJAutoProxy
@Component
public class App extends Application {

    public static final SpringFxmlLoader LOADER = new SpringFxmlLoader();

    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) LOADER.load(FXMLS.LOGIN_FORM.toString());
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
}
