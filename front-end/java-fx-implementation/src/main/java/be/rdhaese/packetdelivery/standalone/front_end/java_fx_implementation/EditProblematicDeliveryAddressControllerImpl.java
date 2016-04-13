package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.DeliveryAddressDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.EditProblematicDeliveryAddressController;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.ProblematicDeliveryController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 16/01/2016.
 *
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
        System.out.println(location);
        cmbbxRegions.getItems().addAll(regionsService.regions());

        DeliveryAddressDTO deliveryAddressDTO = problematicPacketsService.getDeliveryAddress(currentPacket);

        RegionDTO currentRegion = new RegionDTO(deliveryAddressDTO.getRegionName(), deliveryAddressDTO.getRegionCode());
        cmbbxRegions.getSelectionModel().select(currentRegion);

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
        deliveryAddressDTO.setRegionName(selectedRegion.getName());
        deliveryAddressDTO.setRegionCode(selectedRegion.getCode());
        if (validateInput(deliveryAddressDTO)){
            problematicPacketsService.saveDeliveryAddress(deliveryAddressDTO);
            problematicDeliveryController.update();
            cancel();
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
        if (!isEmpty(txtStreet)) {
            deliveryAddressDTO.setStreet(txtStreet.getText());
            removeErrorStyleIfNeeded(txtStreet);
            return true;
        }
        markForError(txtStreet);
        return false;
    }

    private boolean validateNumber(DeliveryAddressDTO deliveryAddressDTO) {
        if (!isEmpty(txtNumber)) {
            deliveryAddressDTO.setNumber(txtNumber.getText());
            removeErrorStyleIfNeeded(txtNumber);
            return true;
        }
        markForError(txtNumber);
        return false;
    }

    private boolean validateMailbox(DeliveryAddressDTO deliveryAddressDTO) {
        deliveryAddressDTO.setMailbox(txtMailbox.getText());
        return true;
    }

    private boolean validateCity(DeliveryAddressDTO deliveryAddressDTO) {
        if (!isEmpty(txtCity)) {
            deliveryAddressDTO.setCity(txtCity.getText());
            removeErrorStyleIfNeeded(txtCity);
            return true;
        }
        markForError(txtCity);
        return false;
    }

    private boolean validatePostalCode(DeliveryAddressDTO deliveryAddressDTO) {
        if (!isEmpty(txtPostalCode)) {
            deliveryAddressDTO.setPostalCode(txtPostalCode.getText());
            removeErrorStyleIfNeeded(txtPostalCode);
            return true;
        }
        markForError(txtPostalCode);
        return false;
    }

    @Override
    public void cancel() {
        //TODO ask if user is sure
        Stage stage = (Stage) txtStreet.getScene().getWindow();
        stage.close();
    }

    public  void setCurrentPacket(String currentPacket) {
        this.currentPacket = currentPacket;
    }
}
