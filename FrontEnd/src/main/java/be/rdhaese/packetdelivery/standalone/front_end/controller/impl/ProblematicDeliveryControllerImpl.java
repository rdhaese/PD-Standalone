package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.ProblematicDeliveryController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.service.ProblematicPacketsService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class ProblematicDeliveryControllerImpl extends AbstractWithMenuAndStatusBarController implements ProblematicDeliveryController {

    private static String currentPacket;

    @Autowired
    private DateFormat dateFormat;
    @Autowired
    private ProblematicPacketsService problematicPacketsService;

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
        if ((currentPacket == null) || ((currentPacket = currentPacket.trim()).isEmpty())){
            showOverview(lblLoggedInUsername.getScene(), getMessage("toolbar.message.noProblematicPacketToViewDetailsFrom"));
        }else {
            PacketDTO problematicPacket = problematicPacketsService.getProblematicPacket(currentPacket);
            initializeHeaderLabels(problematicPacket);
            initializeClientLabels(problematicPacket);
            initializeDeliveryLabels(problematicPacket);
            super.initialize(location, resources);
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
        lblDeliveryRegion.setText(problematicPacket.getDeliverRegionName());
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

    }

    @Override
    public void reSend() {
        problematicPacketsService.reSend(currentPacket);
    }

    @Override
    public void returnToSender() {
        problematicPacketsService.returnToSender(currentPacket);
    }

    @Override
    public void cancel() {
        showScene(lblLoggedInUsername.getScene(), FXMLS.PROBLEMATIC_DELIVERIES);
    }

    public static void setCurrentPacket(String currentPacket) {
        ProblematicDeliveryControllerImpl.currentPacket = currentPacket;
    }
}
