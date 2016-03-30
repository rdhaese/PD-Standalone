package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.controller.OptionsController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractInitializeableController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 29/02/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class OptionsControllerImpl extends AbstractInitializeableController implements OptionsController {

    @FXML
    private ComboBox<String> cmbbxLanguage;

    @FXML
    private ToggleGroup grpPrintPreference;

    @FXML
    private CheckBox cboxShowInImageViewer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO place this functionality in its appropriate layerss
        //TODO get supported languages
        //TODO check in file if there is line with username of logged in username
        //TODO --if so, mark the language in the line, in the checkbox
        //TODO --if so, select the preferred print option, or ask if no line present
        //TODO --if so, select the checkbox according to the option in the line, enabled if no line present
    }

    @Override
    public void cancel() {
        Stage stage = (Stage) cmbbxLanguage.getScene().getWindow();
        stage.close();
    }

    @Override
    public void save() {
        //TODO remove line for logged in user if it is present
        //TODO create new line
        //TODO add the line to file
        OverviewControllerImpl.setMessage(getMessage("toolbar.message.optionsSaved"));
        Stage stage = (Stage) cmbbxLanguage.getScene().getWindow();
        stage.close();
    }
}
