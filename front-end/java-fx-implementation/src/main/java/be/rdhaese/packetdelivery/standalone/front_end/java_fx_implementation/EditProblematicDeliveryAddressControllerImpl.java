package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.EditProblematicDeliveryAddressController;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.ProblematicDeliveryController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.RegionDTOLocaleAwareToString.RegionDtoLocaleAwareToString;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert.AlertTool;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation.Validator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

/**
 * @author Robin D'Haese
 */
@Controller
public class EditProblematicDeliveryAddressControllerImpl extends AbstractInitializeableController implements EditProblematicDeliveryAddressController {

    private String currentPacket;

    @Autowired
    private ProblematicDeliveryController problematicDeliveryController;
    @Autowired
    private ProblematicPacketsWebService problematicPacketsService;
    @Autowired
    private RegionsWebService regionsService;
    @Autowired
    private Validator validator;
    @Autowired
    private AlertTool alertTool;

    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtMailbox;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private ComboBox<RegionDTO> cmbbxRegions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtStreet.requestFocus();
            }
        });

        List<RegionDtoLocaleAwareToString> regions = new ArrayList<>();
        for (RegionDTO regionDTO : regionsService.regions()) {
            regions.add(new RegionDtoLocaleAwareToString(LocaleContextHolder.getLocale(), regionDTO));
        }
        cmbbxRegions.getItems().addAll(regions);

        DeliveryAddressDTO deliveryAddressDTO = problematicPacketsService.getDeliveryAddress(currentPacket);


        RegionDTO currentRegion = new RegionDTO(deliveryAddressDTO.getRegionNameNl(), deliveryAddressDTO.getRegionNameFr(), deliveryAddressDTO.getRegionNameDe(), deliveryAddressDTO.getRegionNameEn(), deliveryAddressDTO.getRegionCode());
        RegionDtoLocaleAwareToString currentRegionDtoLocaleAwareToString = new RegionDtoLocaleAwareToString(LocaleContextHolder.getLocale(), currentRegion);
        cmbbxRegions.getSelectionModel().select(currentRegionDtoLocaleAwareToString);


        txtStreet.setText(deliveryAddressDTO.getStreet());
        txtNumber.setText(deliveryAddressDTO.getNumber());
        txtMailbox.setText(deliveryAddressDTO.getMailbox());
        txtCity.setText(deliveryAddressDTO.getCity());
        txtPostalCode.setText(deliveryAddressDTO.getPostalCode());
    }

    @Override
    public void save() {
        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryAddressDTO.setPacketId(currentPacket);
        RegionDTO selectedRegion = cmbbxRegions.getSelectionModel().getSelectedItem();
        deliveryAddressDTO.setRegionNameNl(selectedRegion.getNameNl());
        deliveryAddressDTO.setRegionNameFr(selectedRegion.getNameFr());
        deliveryAddressDTO.setRegionNameDe(selectedRegion.getNameDe());
        deliveryAddressDTO.setRegionNameEn(selectedRegion.getNameEn());
        deliveryAddressDTO.setRegionCode(selectedRegion.getCode());
        if (validateInput(deliveryAddressDTO)) {
            problematicPacketsService.saveDeliveryAddress(deliveryAddressDTO);
            messageHolder.setMessage(getMessage("toolbar.message.addressChanged"));
            problematicDeliveryController.update();
            closeWindow();
        }
    }

    private boolean validateInput(DeliveryAddressDTO deliveryAddressDTO) {
        return validateStreet(deliveryAddressDTO) &&
                validateNumber(deliveryAddressDTO) &&
                validateMailbox(deliveryAddressDTO) &&
                validateCity(deliveryAddressDTO) &&
                validatePostalCode(deliveryAddressDTO);
    }

    private boolean validateStreet(DeliveryAddressDTO deliveryAddressDTO) {
        if (validator.isValidStreet(txtStreet.getText())) {
            deliveryAddressDTO.setStreet(txtStreet.getText());
            removeErrorStyleIfNeeded(txtStreet);
            return true;
        }
        markForError(txtStreet, "problematicDelivery.editAddress.tooltip.street");
        return false;
    }

    private boolean validateNumber(DeliveryAddressDTO deliveryAddressDTO) {
        if (validator.isValidNumber(txtNumber.getText())) {
            deliveryAddressDTO.setNumber(txtNumber.getText());
            removeErrorStyleIfNeeded(txtNumber);
            return true;
        }
        markForError(txtNumber, "problematicDelivery.editAddress.tooltip.number");
        return false;
    }

    private boolean validateMailbox(DeliveryAddressDTO deliveryAddressDTO) {
        if (validator.isValidMailbox(txtMailbox.getText())) {
            deliveryAddressDTO.setMailbox(txtMailbox.getText());
            removeErrorStyleIfNeeded(txtMailbox);
            return true;
        }
        markForError(txtMailbox, "problematicDelivery.editAddress.tooltip.mailbox");
        return false;
    }

    private boolean validateCity(DeliveryAddressDTO deliveryAddressDTO) {
        if (validator.isValidCity(txtCity.getText())) {
            deliveryAddressDTO.setCity(txtCity.getText());
            removeErrorStyleIfNeeded(txtCity);
            return true;
        }
        markForError(txtCity, "problematicDelivery.editAddress.tooltip.city");
        return false;
    }

    private boolean validatePostalCode(DeliveryAddressDTO deliveryAddressDTO) {
        if (validator.isValidPostalCode(txtPostalCode.getText())) {
            deliveryAddressDTO.setPostalCode(txtPostalCode.getText());
            removeErrorStyleIfNeeded(txtPostalCode);
            return true;
        }
        markForError(txtPostalCode, "problematicDelivery.editAddress.tooltip.postalCode");
        return false;
    }

    @Override
    public void cancel() {
        Optional<ButtonType> result = alertTool.getCancelAlert().showAndWait();

        if (result.get() == ButtonType.OK) {
            closeWindow();
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) txtStreet.getScene().getWindow();
        stage.close();
    }

    public void setCurrentPacket(String currentPacket) {
        this.currentPacket = currentPacket;
    }
}
