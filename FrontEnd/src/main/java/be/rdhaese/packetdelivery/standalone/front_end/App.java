package be.rdhaese.packetdelivery.standalone.front_end;

import be.rdhaese.packetdelivery.standalone.front_end.controller.LoginFormController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.service.AuthenticationService;
import be.rdhaese.packetdelivery.standalone.service.ContactInformationService;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class App extends Application {

    public static final SpringFxmlLoader LOADER = new SpringFxmlLoader();

    public static final Image ICON = new Image(App.class.getResourceAsStream("/img/icon.png"));

    public static void main( String[] args )
    {
        LauncherImpl.launchApplication(App.class,SplashPreLoader.class,args);
    }

    private Scene scene;
    @Override
    public void init() throws Exception {
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.25));
        Parent root = (Parent) LOADER.load(FXMLS.LOGIN_FORM.toString());
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.50));
        scene = new Scene(root, 1024, 768);
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.75));

        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(ICON);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
