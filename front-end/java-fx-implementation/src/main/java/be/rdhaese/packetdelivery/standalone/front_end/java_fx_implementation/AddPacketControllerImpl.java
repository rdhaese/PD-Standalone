package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.AddPacketController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.RegionDTOLocaleAwareToString.RegionDtoLocaleAwareToString;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert.AlertTool;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.from_url.FromUrlResultHolder;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.*;

/**
 *
 * @author Robin D'Haese
 */
@Controller
public class AddPacketControllerImpl extends AbstractWithMenuAndStatusBarController implements AddPacketController {

    private static final String COMBOBOX_ERROR_STYLE_CLASS = "cberror";
    public static final String PACKET_STATUS_NORMAL = "NORMAL";
    @Autowired
    private AddPacketWebService addPacketService;
    @Autowired
    private RegionsWebService regionsService;
    @Autowired
    private AlertTool alertTool;
    @Autowired
    private FromUrlResultHolder fromUrlResultHolder;

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

    @FXML
    private Button btnGetInformationFromURL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        if (cmbbxDeliveryRegion == null) {
            cmbbxDeliveryRegion = new ComboBox<>();
        }
        Collection<RegionDtoLocaleAwareToString> regions = new ArrayList<>();
        for (RegionDTO regionDTO : regionsService.regions()) {
            regions.add(new RegionDtoLocaleAwareToString(LocaleContextHolder.getLocale(), regionDTO));
        }
        cmbbxDeliveryRegion.getItems().addAll(regions);


        txtClientName.textProperty().bindBidirectional(fromUrlResultHolder.clientNameProperty());
        txtClientPhone.textProperty().bindBidirectional(fromUrlResultHolder.clientPhoneProperty());
        txtClientEmail.textProperty().bindBidirectional(fromUrlResultHolder.clientEmailProperty());
        txtClientStreet.textProperty().bindBidirectional(fromUrlResultHolder.clientStreetProperty());
        txtClientNumber.textProperty().bindBidirectional(fromUrlResultHolder.clientNumberProperty());
        txtClientMailbox.textProperty().bindBidirectional(fromUrlResultHolder.clientMailboxProperty());
        txtClientPostalCode.textProperty().bindBidirectional(fromUrlResultHolder.clientPostalCodeProperty());
        txtClientCity.textProperty().bindBidirectional(fromUrlResultHolder.clientCityProperty());

        txtDeliveryName.textProperty().bindBidirectional(fromUrlResultHolder.deliveryNameProperty());
        txtDeliveryPhone.textProperty().bindBidirectional(fromUrlResultHolder.deliveryPhoneProperty());
        txtDeliveryEmail.textProperty().bindBidirectional(fromUrlResultHolder.deliveryEmailProperty());
        txtDeliveryStreet.textProperty().bindBidirectional(fromUrlResultHolder.deliveryStreetProperty());
        txtDeliveryNumber.textProperty().bindBidirectional(fromUrlResultHolder.deliveryNumberProperty());
        txtDeliveryMailbox.textProperty().bindBidirectional(fromUrlResultHolder.deliveryMailboxProperty());
        txtDeliveryPostalCode.textProperty().bindBidirectional(fromUrlResultHolder.deliveryPostalCodeProperty());
        txtDeliveryCity.textProperty().bindBidirectional(fromUrlResultHolder.deliveryCityProperty());
    }

    public void informationFromURL() {
        cmbbxDeliveryRegion.requestFocus();
        showInNewWindow(FXMLS.FROM_URL, "fromUrl.title", 450, 100, false);
    }

    public void cancel() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Optional<ButtonType> result = alertTool.getCancelAlert().showAndWait();

                if (result.get() == ButtonType.OK) {
                    clearFromUrlResultHolder();
                    showOverview(txtClientName.getScene(), null);
                }
            }
        });
    }

    public void addPacket() {
        PacketDTO packetDTO = new PacketDTO();
        if (allInputIsValid(packetDTO)) {
            packetDTO.setPacketStatus(PACKET_STATUS_NORMAL);
            packetDTO.setStatusChangedOn(new Date());
            try {
                addPacketService.addPacket(packetDTO);
            } catch (Exception e) {
                //Do nothing, exception should already be logged and handled by aspects
                //Throw a runtime exception so this exception is not swallowed if for some reason the aspects are not working
                throw new RuntimeException(e);
            }
            clearFromUrlResultHolder();
            showOverview(txtClientName.getScene(), getMessage("toolbar.message.packetAddedSuccessful"));
        }
        //Do nothing -> Keep showing form, so input can be corrected.
    }

    private void clearFromUrlResultHolder() {
        fromUrlResultHolder.setClientName(null);
        fromUrlResultHolder.setClientPhone(null);
        fromUrlResultHolder.setClientEmail(null);
        fromUrlResultHolder.setClientStreet(null);
        fromUrlResultHolder.setClientNumber(null);
        fromUrlResultHolder.setClientMailbox(null);
        fromUrlResultHolder.setClientPostalCode(null);
        fromUrlResultHolder.setClientCity(null);

        fromUrlResultHolder.setDeliveryName(null);
        fromUrlResultHolder.setDeliveryPhone(null);
        fromUrlResultHolder.setDeliveryEmail(null);
        fromUrlResultHolder.setDeliveryStreet(null);
        fromUrlResultHolder.setDeliveryNumber(null);
        fromUrlResultHolder.setDeliveryMailbox(null);
        fromUrlResultHolder.setDeliveryPostalCode(null);
        fromUrlResultHolder.setDeliveryCity(null);
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
        if (validator.isValidClientName(txtClientName.getText())) {
            packetDTO.setClientName(txtClientName.getText());
            removeErrorStyleIfNeeded(txtClientName);
            return true;
        }
        markForError(txtClientName, "addPacket.tooltip.name");
        return false;
    }

    private boolean validateClientPhone(PacketDTO packetDTO) {
        if (validator.isValidPhoneNumber(txtClientPhone.getText())) {
            packetDTO.setClientPhone(txtClientPhone.getText());
            removeErrorStyleIfNeeded(txtClientPhone);
            return true;
        }
        markForError(txtClientPhone, "addPacket.tooltip.phone");
        return false;
    }

    private boolean validateClientEmail(PacketDTO packetDTO) {
        if (validator.isValidEmailAddress(txtClientEmail.getText())) {
            packetDTO.setClientEmail(txtClientEmail.getText());
            removeErrorStyleIfNeeded(txtClientEmail);
            return true;
        }
        markForError(txtClientEmail, "addPacket.tooltip.email");
        return false;
    }

    private boolean validateClientStreet(PacketDTO packetDTO) {
        if (validator.isValidStreet(txtClientStreet.getText())) {
            packetDTO.setClientStreet(txtClientStreet.getText());
            removeErrorStyleIfNeeded(txtClientStreet);
            return true;
        }
        markForError(txtClientStreet, "addPacket.tooltip.street");
        return false;
    }

    private boolean validateClientNumber(PacketDTO packetDTO) {
        if (validator.isValidNumber(txtClientNumber.getText())) {
            packetDTO.setClientNumber(txtClientNumber.getText());
            removeErrorStyleIfNeeded(txtClientNumber);
            return true;
        }
        markForError(txtClientNumber, "addPacket.tooltip.number");
        return false;
    }

    private boolean validateClientMailbox(PacketDTO packetDTO) {
        if (validator.isValidMailbox(txtClientMailbox.getText())) {
            packetDTO.setClientMailbox(txtClientMailbox.getText());
            removeErrorStyleIfNeeded(txtClientMailbox);
            return true;
        }
        markForError(txtClientMailbox, "addPacket.tooltip.mailbox");
        return false;
    }

    private boolean validateClientCity(PacketDTO packetDTO) {
        if (validator.isValidCity(txtClientCity.getText())) {
            packetDTO.setClientCity(txtClientCity.getText());
            removeErrorStyleIfNeeded(txtClientCity);
            return true;
        }
        markForError(txtClientCity, "addPacket.tooltip.city");
        return false;
    }

    private boolean validateClientPostalCode(PacketDTO packetDTO) {
        if (validator.isValidPostalCode(txtClientPostalCode.getText())) {
            packetDTO.setClientPostalCode(txtClientPostalCode.getText());
            removeErrorStyleIfNeeded(txtClientPostalCode);
            return true;
        }
        markForError(txtClientPostalCode, "addPacket.tooltip.postalCode");
        return false;
    }

    private boolean validateDeliveryName(PacketDTO packetDTO) {
        if (validator.isValidClientName(txtDeliveryName.getText())) {
            packetDTO.setDeliveryName(txtDeliveryName.getText());
            removeErrorStyleIfNeeded(txtDeliveryName);
            return true;
        }
        markForError(txtDeliveryName, "addPacket.tooltip.name");
        return false;
    }

    private boolean validateDeliveryPhone(PacketDTO packetDTO) {
        if (validator.isValidPhoneNumber(txtDeliveryPhone.getText())) {
            packetDTO.setDeliveryPhone(txtDeliveryPhone.getText());
            removeErrorStyleIfNeeded(txtDeliveryPhone);
            return true;
        }
        markForError(txtDeliveryPhone, "addPacket.tooltip.phone");
        return false;
    }

    private boolean validateDeliveryEmail(PacketDTO packetDTO) {
        if (validator.isValidEmailAddress(txtDeliveryEmail.getText())) {
            packetDTO.setDeliveryEmail(txtDeliveryEmail.getText());
            removeErrorStyleIfNeeded(txtDeliveryEmail);
            return true;
        }
        markForError(txtDeliveryEmail, "addPacket.tooltip.email");
        return false;
    }

    private boolean validateDeliveryStreet(PacketDTO packetDTO) {
        if (validator.isValidStreet(txtDeliveryStreet.getText())) {
            packetDTO.setDeliveryStreet(txtDeliveryStreet.getText());
            removeErrorStyleIfNeeded(txtDeliveryStreet);
            return true;
        }
        markForError(txtDeliveryStreet, "addPacket.tooltip.street");
        return false;
    }

    private boolean validateDeliveryNumber(PacketDTO packetDTO) {
        if (validator.isValidNumber(txtDeliveryNumber.getText())) {
            packetDTO.setDeliveryNumber(txtDeliveryNumber.getText());
            removeErrorStyleIfNeeded(txtDeliveryNumber);
            return true;
        }
        markForError(txtDeliveryNumber, "addPacket.tooltip.number");
        return false;
    }

    private boolean validateDeliveryMailbox(PacketDTO packetDTO) {
        if (validator.isValidMailbox(txtDeliveryMailbox.getText())) {
            packetDTO.setClientMailbox(txtDeliveryMailbox.getText());
            removeErrorStyleIfNeeded(txtDeliveryMailbox);
            return true;
        }
        markForError(txtDeliveryMailbox, "addPacket.tooltip.mailbox");
        return false;
    }

    private boolean validateDeliveryCity(PacketDTO packetDTO) {
        if (validator.isValidCity(txtDeliveryCity.getText())) {
            packetDTO.setDeliveryCity(txtDeliveryCity.getText());
            removeErrorStyleIfNeeded(txtDeliveryCity);
            return true;
        }
        markForError(txtDeliveryCity, "addPacket.tooltip.city");
        return false;
    }

    private boolean validateDeliveryPostalCode(PacketDTO packetDTO) {
        if (validator.isValidPostalCode(txtDeliveryPostalCode.getText())) {
            packetDTO.setDeliveryPostalCode(txtDeliveryPostalCode.getText());
            removeErrorStyleIfNeeded(txtDeliveryPostalCode);
            return true;
        }
        markForError(txtDeliveryPostalCode, "addPacket.tooltip.postalCode");
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
