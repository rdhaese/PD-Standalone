package be.rdhaese.packetdelivery.standalone.front_end;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
public class SplashPreLoader extends Preloader {

    Stage stage;

    private Scene createPreloaderScene() {
        ImageView imageView = new ImageView(App.ICON);
        Label lblLoading = new Label("Loading..."); //TODO Internationalize
        lblLoading.setFont(Font.font("monospaced", FontWeight.LIGHT, 24));
        BorderPane p = new BorderPane();
        p.setTop(lblLoading);
        p.setCenter(imageView);
        return new Scene(p, 500, 300);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().addAll(App.ICON);
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
       //do nothing
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}
