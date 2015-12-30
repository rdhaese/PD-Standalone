package be.rdhaese.packetdelivery.standalone.front_end.controller;

import be.rdhaese.project.App;
import be.rdhaese.project.dto.PacketDTO;
import be.rdhaese.project.dto.RegionDTO;
import be.rdhaese.project.standalone_service.AddPacketService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created on 23/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class AddPacketController extends AbstractController {

    @Autowired
    private AddPacketService addPacketService;

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
        Collection<RegionDTO> regions = addPacketService.getRegions();
        for (Object region : regions){
            System.out.println(region);
            System.out.println(region.getClass());
            System.out.println(region.toString());
        }
       cmbbxDeliveryRegion.getItems().addAll(regions);
        super.initialize(location, resources);
    }

    public void informationFromURL() {
        Parent root = (Parent) App.LOADER.load("from-url");
        Stage stage = new Stage();
        stage.setTitle("Get Information From URL");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }

    public void cancel() {
        showOverview(txtClientName.getScene(), null);
    }

    public void addPacket() {
        //TODO Validation should be better (ie phone, email, postal code,.....)
        //TODO Multiple phones and emails? -> split on comma?
        PacketDTO packetDTO = new PacketDTO();
        if (allInputIsValid(packetDTO)) {
            String packetId = addPacketService.addPacket(packetDTO);
            showOverview(txtClientName.getScene(), "Packet added successfully");
            generateAndShowQRCode(packetId);
        }
        //Do nothing -> Keep showing form, so input can be corrected.
    }

    private void generateAndShowQRCode(String packetID) {
        try {
            BufferedImage image = createQRCodeImage(packetID);
            File qrCode = saveImage(packetID, image);
            askForPrint(packetID, qrCode);
            Desktop.getDesktop().open(qrCode); //TODO test generated QR code, scan with smartphone and check content
        } catch (IOException ioe) {
            //TODO log error
            ioe.printStackTrace();
        }
    }

    private static void askForPrint(String packetID, File qrCode) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Print QR-Code");
        alert.setHeaderText(String.format("Packet ID: %s", packetID));
        alert.setContentText(String.format("Start printing QR-code for packet %s?", packetID));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Desktop.getDesktop().print(qrCode);
        }
        //Do nothing if OK is not clicked.
    }

    private File saveImage(String packetID, BufferedImage image) throws IOException {
        File f = new File(String.format("%s%s.jpg", getLocationsToSave(), packetID)); //TODO, location on mac and linux? Should be known before presenting
        if (!f.exists()) {
            f.createNewFile();
        }
        ImageIO.write(image, "jpg", f);
        return f;
    }

    private String getLocationsToSave() throws IOException {
        String textualPath = String.format("%s%spacket-delivery-system%scodes%s",
                System.getProperty("user.home"),
                File.separator, File.separator, File.separator);
        Paths.get(textualPath).toFile().mkdirs();
        return textualPath;
    }

    private BufferedImage createQRCodeImage(String packetID) throws IOException {
        BufferedImage image = ImageIO.read(QRCode.from(packetID).withSize(250, 250).file());
        addText(packetID, image.getGraphics());
        return image;
    }

    private void addText(String packetID, Graphics g) {
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.black);
        g.drawString("Packet ID:", 10, 20);
        g.drawString(packetID, 10, 240);
        g.dispose();
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
        if (styleClass.contains("cberror")) {
            styleClass.remove("cberror");
        }
    }

    private void markComboBoxForError(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (!styleClass.contains("cberror")) {
            styleClass.add("cberror");
        }
    }
}
