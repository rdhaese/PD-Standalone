package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.OptionsController;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.OverviewController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert.AlertTool;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SplashPreLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created on 29/02/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class OptionsControllerImpl extends AbstractInitializeableController implements OptionsController {

    @Autowired
    private OverviewController overviewController;

    @Autowired
    private OptionsWebService optionsService;

    @Autowired
    private AlertTool alertTool;

    @FXML
    private ComboBox<String> cmbbxLanguage;

    @FXML
    private ToggleGroup grpPrintPreference;

    @FXML
    private RadioButton rbtnAskBeforePrint;

    @FXML
    private RadioButton rbtnDontAskAndPrint;

    @FXML
    private RadioButton rbtnDontAskAndDontPrint;

    @FXML
    private CheckBox cbxOpenInImageViewer;

    private String originalLanguage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Fill language combobox with supported languages
        cmbbxLanguage.getItems().addAll(OptionsWebService.SUPPORTED_LANGUAGES);

        //Get options for logged in user from back-end
        OptionsDTO optionsDTO = optionsService.getFor(authenticationService.getLoggedInUser());

        //Select language in combobox that is set in dto
        cmbbxLanguage.getSelectionModel().select(optionsDTO.getLanguage());
        //Set the language as original language
        originalLanguage = optionsDTO.getLanguage();

        //Select print option that is set in dto
        switch (optionsDTO.getPrint()) {
            case 0: //OptionsWebService.NEVER:
                grpPrintPreference.selectToggle(rbtnDontAskAndDontPrint);
                break;
            case 1: //OptionsWebService.ASK:
                grpPrintPreference.selectToggle(rbtnAskBeforePrint);
                break;
            case 2: //OptionsWebService.PRINT:
                grpPrintPreference.selectToggle(rbtnDontAskAndPrint);
                break;
        }

        //Set show in image viewer according to dto
        if (optionsDTO.getImageViewer()) {
            cbxOpenInImageViewer.setSelected(true);
        } else {
            cbxOpenInImageViewer.setSelected(false);
        }
    }

    @Override
    public void cancel() {
        Optional<ButtonType> result = alertTool.getCancelAlert().showAndWait();

        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) cmbbxLanguage.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void save() {
        //Create optionsDTO to send to back-end
        OptionsDTO optionsDTO = new OptionsDTO();

        //Set current logged in user as user for the options
        optionsDTO.setUser(authenticationService.getLoggedInUser());

        //Set selected language in dto
        optionsDTO.setLanguage(cmbbxLanguage.getSelectionModel().getSelectedItem());

        //Set selected print option in dto
        if (rbtnDontAskAndDontPrint.isSelected()) {
            optionsDTO.setPrint(OptionsWebService.NEVER);
        } else if (rbtnAskBeforePrint.isSelected()) {
            optionsDTO.setPrint(OptionsWebService.ASK);
        } else {
            optionsDTO.setPrint(OptionsWebService.PRINT);
        }

        //Set selected image viewer option in dto
        optionsDTO.setImageViewer(cbxOpenInImageViewer.isSelected());

        //Pass dto to back end so the options get saved
        optionsService.save(optionsDTO);

        //Save the print and imageViewer options in the system properties
        System.setProperty("print", optionsDTO.getPrint().toString());
        System.setProperty("imageViewer", optionsDTO.getImageViewer().toString());

        //Check if language has changed
        if (!originalLanguage.equals(optionsDTO.getLanguage())){
            //Show message that the language change needs a restart
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(SplashPreLoader.ICON);
            alert.setTitle(messageSource.getMessage("options.languageChanged.title", null, LocaleContextHolder.getLocale()));
            alert.setHeaderText(messageSource.getMessage("options.languageChanged.header", null, LocaleContextHolder.getLocale()));
            alert.setContentText(messageSource.getMessage("options.languageChanged.content", null, LocaleContextHolder.getLocale()));
            alert.show();
        }

        //Close the options window
        Stage stage = (Stage) cmbbxLanguage.getScene().getWindow();
        stage.close();
    }
}
