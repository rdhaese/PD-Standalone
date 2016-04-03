package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.interfaces.AboutController;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 22/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class AboutControllerImpl implements AboutController {


    @FXML
    private WebView wvAbout;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String url = AboutControllerImpl.class.getResource("/html/about.html").toExternalForm();
        wvAbout.getEngine().load(url);
        wvAbout.setContextMenuEnabled(false);
    }
}
