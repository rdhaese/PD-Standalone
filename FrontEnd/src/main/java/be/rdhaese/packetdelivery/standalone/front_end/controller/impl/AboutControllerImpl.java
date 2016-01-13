package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.AboutController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.File;
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
