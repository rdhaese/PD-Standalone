package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;


/**
 *
 * @author Robin D'Haese
 */
@Component
public class SplashPreLoader extends Preloader {

    public static final Image ICON = new Image(SplashPreLoader.class.getResourceAsStream("/img/icon.png"));
    private static final String LOADING_TEXT = "Loading...";
    private static final String LOADING_TEXT_FONT = "monospaced";

    Stage stage;

    private Scene createPreloaderScene() {
        ImageView imageView = new ImageView(ICON);
        Label lblLoading = new Label(LOADING_TEXT);
        lblLoading.setFont(Font.font(LOADING_TEXT_FONT, FontWeight.LIGHT, 24));
        BorderPane p = new BorderPane();
        p.setTop(lblLoading);
        p.setCenter(imageView);
        return new Scene(p, 500, 300);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().addAll(ICON);
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
