package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.AddPacketController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created on 23/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class AddPacketControllerImpl extends AbstractWithMenuAndStatusBarController implements AddPacketController {

    private static final String COMBOBOX_ERROR_STYLE_CLASS = "cberror";
    @Autowired
    private AddPacketWebService addPacketService;
    @Autowired
    RegionsWebService regionsService;

    @FXML
    private TextField txtClientName;
    @FXML
    private TextField txtClientPhone;
    @FXML
    private TextField txtClientEmail;
    @FXML
    private TextField txtClientStreet;
    @FXML
    private TextField txtClientNumber;
    @FXML
    private TextField txtClientMailbox;
    @FXML
    private TextField txtClientCity;
    @FXML
    private TextField txtClientPostalCode;
    @FXML
    private TextField txtDeliveryName;
    @FXML
    private TextField txtDeliveryPhone;
    @FXML
    private TextField txtDeliveryEmail;
    @FXML
    private TextField txtDeliveryStreet;
    @FXML
    private TextField txtDeliveryNumber;
    @FXML
    private TextField txtDeliveryMailbox;
    @FXML
    private TextField txtDeliveryCity;
    @FXML
    private TextField txtDeliveryPostalCode;
    @FXML
    private ComboBox<RegionDTO> cmbbxDeliveryRegion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (cmbbxDeliveryRegion == null){
            cmbbxDeliveryRegion = new ComboBox<>();
        }
        Collection<RegionDTO> regions = regionsService.regions();
        cmbbxDeliveryRegion.getItems().addAll(regions);
        super.initialize(location, resources);
    }

    public void informationFromURL() {
        showInNewWindow(FXMLS.FROM_URL, "fromUrl.title", 450, 100 , false);
    }

    public void cancel() {
        //TODO ask if user is sure
        showOverview(txtClientName.getScene(), null);
    }

    public void addPacket() {
        //TODO Validation should be better (ie phone, email, postal code,.....)
        //TODO Multiple phones and emails? -> split on comma?
        PacketDTO packetDTO = new PacketDTO();
        if (allInputIsValid(packetDTO)) {
            packetDTO.setPacketStatus("NORMAL");
            packetDTO.setStatusChangedOn(new Date());
            addPacketService.addPacket(packetDTO);
            showOverview(txtClientName.getScene(), getMessage("toolbar.message.packetAddedSuccessful"));
        }
        //Do nothing -> Keep showing form, so input can be corrected.
    }

    private boolean allInputIsValid(PacketDTO packetDTO) {
        return validateClientName(packetDTO)
                & validateClientPhone(packetDTO)
                & validateClientEmail(packetDTO)
                & validateClientStreet(packetDTO)
                & validateClientNumber(packetDTO)
                & validateClientMailbox(packetDTO)
                & validateClientCity(packetDTO)
                & validateClientPostalCode(packetDTO)
                & validateDeliveryName(packetDTO)
                & validateDeliveryPhone(packetDTO)
                & validateDeliveryEmail(packetDTO)
                & validateDeliveryStreet(packetDTO)
                & validateDeliveryNumber(packetDTO)
                & validateDeliveryMailbox(packetDTO)
                & validateDeliveryCity(packetDTO)
                & validateDeliveryPostalCode(packetDTO)
                & validateDeliveryRegion(packetDTO);
    }

    private boolean validateClientName(PacketDTO packetDTO) {
        if (!isEmpty(txtClientName)) {
            packetDTO.setClientName(txtClientName.getText());
            removeErrorStyleIfNeeded(txtClientName);
            return true;
        }
        markForError(txtClientName);
        return false;
    }

    private boolean validateClientPhone(PacketDTO packetDTO) {
        if (!isEmpty(txtClientPhone)) {
            packetDTO.setClientPhone(txtClientPhone.getText());
            removeErrorStyleIfNeeded(txtClientPhone);
            return true;
        }
        markForError(txtClientPhone);
        return false;
    }

    private boolean validateClientEmail(PacketDTO packetDTO) {
        if (!isEmpty(txtClientEmail)) {
            packetDTO.setClientEmail(txtClientEmail.getText());
            removeErrorStyleIfNeeded(txtClientEmail);
            return true;
        }
        markForError(txtClientEmail);
        return false;
    }

    private boolean validateClientStreet(PacketDTO packetDTO) {
        if (!isEmpty(txtClientStreet)) {
            packetDTO.setClientStreet(txtClientStreet.getText());
            removeErrorStyleIfNeeded(txtClientStreet);
            return true;
        }
        markForError(txtClientStreet);
        return false;
    }

    private boolean validateClientNumber(PacketDTO packetDTO) {
        if (!isEmpty(txtClientNumber)) {
            packetDTO.setClientNumber(txtClientNumber.getText());
            removeErrorStyleIfNeeded(txtClientNumber);
            return true;
        }
        markForError(txtClientNumber);
        return false;
    }

    private boolean validateClientMailbox(PacketDTO packetDTO) {
        packetDTO.setClientMailbox(txtClientMailbox.getText());
        return true;
    }

    private boolean validateClientCity(PacketDTO packetDTO) {
        if (!isEmpty(txtClientCity)) {
            packetDTO.setClientCity(txtClientCity.getText());
            removeErrorStyleIfNeeded(txtClientCity);
            return true;
        }
        markForError(txtClientCity);
        return false;
    }

    private boolean validateClientPostalCode(PacketDTO packetDTO) {
        if (!isEmpty(txtClientPostalCode)) {
            packetDTO.setClientPostalCode(txtClientPostalCode.getText());
            removeErrorStyleIfNeeded(txtClientPostalCode);
            return true;
        }
        markForError(txtClientPostalCode);
        return false;
    }

    private boolean validateDeliveryName(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryName)) {
            packetDTO.setDeliveryName(txtDeliveryName.getText());
            removeErrorStyleIfNeeded(txtDeliveryName);
            return true;
        }
        markForError(txtDeliveryName);
        return false;
    }

    private boolean validateDeliveryPhone(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryPhone)) {
            packetDTO.setDeliveryPhone(txtDeliveryPhone.getText());
            removeErrorStyleIfNeeded(txtDeliveryPhone);
            return true;
        }
        markForError(txtDeliveryPhone);
        return false;
    }

    private boolean validateDeliveryEmail(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryEmail)) {
            packetDTO.setDeliveryEmail(txtDeliveryEmail.getText());
            removeErrorStyleIfNeeded(txtDeliveryEmail);
            return true;
        }
        markForError(txtDeliveryEmail);
        return false;
    }

    private boolean validateDeliveryStreet(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryStreet)) {
            packetDTO.setDeliveryStreet(txtDeliveryStreet.getText());
            removeErrorStyleIfNeeded(txtDeliveryStreet);
            return true;
        }
        markForError(txtDeliveryStreet);
        return false;
    }

    private boolean validateDeliveryNumber(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryNumber)) {
            packetDTO.setDeliveryNumber(txtDeliveryNumber.getText());
            removeErrorStyleIfNeeded(txtDeliveryNumber);
            return true;
        }
        markForError(txtDeliveryNumber);
        return false;
    }

    private boolean validateDeliveryMailbox(PacketDTO packetDTO) {
        packetDTO.setDeliveryMailbox(txtDeliveryMailbox.getText());
        return true;
    }

    private boolean validateDeliveryCity(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryCity)) {
            packetDTO.setDeliveryCity(txtDeliveryCity.getText());
            removeErrorStyleIfNeeded(txtDeliveryCity);
            return true;
        }
        markForError(txtDeliveryCity);
        return false;
    }

    private boolean validateDeliveryPostalCode(PacketDTO packetDTO) {
        if (!isEmpty(txtDeliveryPostalCode)) {
            packetDTO.setDeliveryPostalCode(txtDeliveryPostalCode.getText());
            removeErrorStyleIfNeeded(txtDeliveryPostalCode);
            return true;
        }
        markForError(txtDeliveryPostalCode);
        return false;
    }

    private boolean validateDeliveryRegion(PacketDTO packetDTO) {
        RegionDTO selectedRegion = cmbbxDeliveryRegion.getValue();
        if (selectedRegion != null) {
            packetDTO.setDeliveryRegionCode(selectedRegion.getCode());
            removeComboBoxErrorStyleIfNeeded(cmbbxDeliveryRegion);
            return true;
        }
        markComboBoxForError(cmbbxDeliveryRegion);
        return false;
    }

    private void removeComboBoxErrorStyleIfNeeded(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (styleClass.contains(COMBOBOX_ERROR_STYLE_CLASS)) {
            styleClass.remove(COMBOBOX_ERROR_STYLE_CLASS);
        }
    }

    private void markComboBoxForError(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (!styleClass.contains(COMBOBOX_ERROR_STYLE_CLASS)) {
            styleClass.add(COMBOBOX_ERROR_STYLE_CLASS);
        }
    }
}
