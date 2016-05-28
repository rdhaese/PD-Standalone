package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ProblematicPacketsWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.EditProblematicDeliveryAddressController;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.ProblematicDeliveriesController;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.ProblematicDeliveryController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.RegionDTOLocaleAwareToString.RegionDtoLocaleAwareToString;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.*;

/**
 *
 * @author Robin D'Haese
 */
@Controller
public class ProblematicDeliveryControllerImpl extends AbstractWithMenuAndStatusBarController implements ProblematicDeliveryController {

    private static String message = null;
    private String currentPacket;

    @Autowired
    private DateFormat dateFormat;
    @Autowired
    private ProblematicDeliveriesController problematicDeliveriesController;
    @Autowired
    private EditProblematicDeliveryAddressController editProblematicDeliveryAddressController;
    @Autowired
    private ProblematicPacketsWebService problematicPacketsService;
    @Autowired
    private RegionsWebService regionService;

    @FXML
    private Label lblPacketId;
    @FXML
    private Label lblStatusChangedOn;
    @FXML
    private Label lblClientName;
    @FXML
    private Label lblClientPhone;
    @FXML
    private Label lblClientEmail;
    @FXML
    private Label lblClientStreet;
    @FXML
    private Label lblClientNumber;
    @FXML
    private Label lblClientMailbox;
    @FXML
    private Label lblClientCity;
    @FXML
    private Label lblClientPostalCode;
    @FXML
    private Label lblDeliveryName;
    @FXML
    private Label lblDeliveryPhone;
    @FXML
    private Label lblDeliveryEmail;
    @FXML
    private Label lblDeliveryStreet;
    @FXML
    private Label lblDeliveryNumber;
    @FXML
    private Label lblDeliveryMailbox;
    @FXML
    private Label lblDeliveryCity;
    @FXML
    private Label lblDeliveryPostalCode;
    @FXML
    private Label lblDeliveryRegion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        super.initialize(location, resources);
    }

    public void init(){
        if ((currentPacket == null) || ((currentPacket = currentPacket.trim()).isEmpty())){
            showOverview(lblLoggedInUsername.getScene(), getMessage("toolbar.message.noProblematicPacketToViewDetailsFrom"));
        }else {
            PacketDTO problematicPacket = problematicPacketsService.getProblematicPacket(currentPacket);
            initializeHeaderLabels(problematicPacket);
            initializeClientLabels(problematicPacket);
            initializeDeliveryLabels(problematicPacket);
        }
    }

    private void initializeDeliveryLabels(PacketDTO problematicPacket) {
        lblDeliveryName.setText(problematicPacket.getDeliveryName());
        lblDeliveryPhone.setText(problematicPacket.getDeliveryPhone());
        lblDeliveryEmail.setText(problematicPacket.getDeliveryEmail());
        lblDeliveryStreet.setText(problematicPacket.getDeliveryStreet());
        lblDeliveryNumber.setText(problematicPacket.getDeliveryNumber());
        String deliveryMailbox;
        if (((deliveryMailbox = problematicPacket.getDeliveryMailbox()) != null) && (!(deliveryMailbox = deliveryMailbox.trim()).isEmpty())){
            lblDeliveryMailbox.setText(deliveryMailbox);
        } else {
            lblDeliveryMailbox.setText(getMessage("problematicDelivery.noMailbox"));
        }
        lblDeliveryCity.setText(problematicPacket.getDeliveryCity());
        lblDeliveryPostalCode.setText(problematicPacket.getDeliveryPostalCode());
        String deliveryRegionName;
        Locale locale = LocaleContextHolder.getLocale();
        if (Locale.forLanguageTag("nl").equals(locale)){
            deliveryRegionName = problematicPacket.getDeliveryRegionNameNl();
        } else if(Locale.forLanguageTag("fr").equals(locale)){
            deliveryRegionName = problematicPacket.getDeliveryRegionNameFr();
        } else if(Locale.forLanguageTag("de").equals(locale)){
            deliveryRegionName = problematicPacket.getDeliveryRegionNameDe();
        } else {
            deliveryRegionName = problematicPacket.getDeliveryRegionNameEn();
        }
        lblDeliveryRegion.setText(deliveryRegionName);
    }

    private void initializeClientLabels(PacketDTO problematicPacket) {
        lblClientName.setText(problematicPacket.getClientName());
        lblClientPhone.setText(problematicPacket.getClientPhone());
        lblClientEmail.setText(problematicPacket.getClientEmail());
        lblClientStreet.setText(problematicPacket.getClientStreet());
        lblClientNumber.setText(problematicPacket.getClientNumber());
        String clientMailbox;
        if (((clientMailbox = problematicPacket.getClientMailbox()) != null) && (!(clientMailbox = clientMailbox.trim()).isEmpty())){
            lblClientMailbox.setText(clientMailbox);
        } else {
            lblClientMailbox.setText(getMessage("problematicDelivery.noMailbox"));
        }
        lblClientCity.setText(problematicPacket.getClientCity());
        lblClientPostalCode.setText(problematicPacket.getClientPostalCode());
    }

    private void initializeHeaderLabels(PacketDTO problematicPacket) {
        lblPacketId.setText(problematicPacket.getPacketId());
        lblStatusChangedOn.setText(dateFormat.format(problematicPacket.getStatusChangedOn()));
    }

    @Override
    public void editAddress() {
        editProblematicDeliveryAddressController.setCurrentPacket(currentPacket);
        showInNewWindow(FXMLS.EDIT_PROBLEMATIC_DELIVERY_ADDRESS, "problematicDelivery.editAddress.title", 400, 400, false);
    }

    @Override
    public void reSend() {
        problematicPacketsService.reSend(currentPacket);
        showScene(lblLoggedInUsername.getScene(), FXMLS.PROBLEMATIC_DELIVERIES, getMessage("toolbar.message.reSend", new Object[]{currentPacket}));
    }

    @Override
    public void returnToSender() {
      Collection<RegionDtoLocaleAwareToString> regions = RegionDtoLocaleAwareToString.mapCollection(regionService.regions(), LocaleContextHolder.getLocale());

        ChoiceDialog<RegionDtoLocaleAwareToString> dialog = new ChoiceDialog<>(null, regions);
        dialog.setTitle(getMessage("askForRegion.title"));
        dialog.setHeaderText(getMessage("askForRegion.header"));
        dialog.setContentText(getMessage("askForRegion.content"));

        Optional<RegionDtoLocaleAwareToString> selectedRegion = dialog.showAndWait();
        if (selectedRegion.isPresent()){
            problematicPacketsService.returnToSender(currentPacket, selectedRegion.get());
            showScene(lblLoggedInUsername.getScene(), FXMLS.PROBLEMATIC_DELIVERIES, getMessage("toolbar.message.returnToSender", new Object[]{currentPacket}));        }
    }

    @Override
    public void cancel() {
        showScene(lblLoggedInUsername.getScene(), FXMLS.PROBLEMATIC_DELIVERIES);
    }

    public void setCurrentPacket(String currentPacket) {
        this.currentPacket = currentPacket;
    }
}
