package be.rdhaese.packetdelivery.standalone.application.java_fx;

import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SplashPreLoader;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SpringFxmlLoader;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@SpringBootApplication (scanBasePackages = "be.rdhaese.packetdelivery.standalone")
@EnableAspectJAutoProxy
public class App extends Application {

    private static SpringApplication application;

    public static void main(String[] args) {
        application = new SpringApplicationBuilder(App.class)
                .headless(false)
                .profiles("production")
                .build();
        LauncherImpl.launchApplication(App.class, SplashPreLoader.class, args);
    }

    private Scene scene;

    @Override
    public void init() throws Exception {
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.25));
        String[] args = new String[getParameters().getRaw().size()];
        getParameters().getRaw().toArray(args);
        SpringFxmlLoader loader = application.run(args).getBean(SpringFxmlLoader.class);
        Parent root = (Parent) loader.load(FXMLS.LOGIN_FORM.toString());
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.50));
        scene = new Scene(root, 1024, 768);
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.75));

        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(SplashPreLoader.ICON);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
