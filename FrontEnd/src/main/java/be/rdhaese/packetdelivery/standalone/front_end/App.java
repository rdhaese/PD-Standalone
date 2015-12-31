package be.rdhaese.packetdelivery.standalone.front_end;

import be.rdhaese.packetdelivery.standalone.front_end.controller.LoginFormController;
import be.rdhaese.packetdelivery.standalone.service.Impl.AuthenticationServiceImpl;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication( scanBasePackages = "be.rdhaese.packetdelivery.standalone")
@EnableAspectJAutoProxy
public class App extends Application {
    public static final SpringFxmlLoader LOADER = new SpringFxmlLoader();

    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) LOADER.load("login-form");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setTitle("PacketDelivery");
        primaryStage.show();
    }
}
