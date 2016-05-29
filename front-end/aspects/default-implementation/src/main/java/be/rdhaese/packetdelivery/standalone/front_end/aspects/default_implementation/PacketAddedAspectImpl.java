package be.rdhaese.packetdelivery.standalone.front_end.aspects.default_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.aspects.interfaces.PacketAddedAspect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.glxn.qrgen.javase.QRCode;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Optional;

/**
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class PacketAddedAspectImpl implements PacketAddedAspect {

    public static final Image ICON = new Image(PacketAddedAspectImpl.class.getResourceAsStream("/img/icon.png"));

    private static final String IMAGE_EXTENSION = "JPEG";
    private static final String IMAGE_TEXT = "Packet ID";
    private static final String LOCATION_TO_SAVE = String.format("%s%spacket-delivery-system%scodes%s",
            System.getProperty("user.home"), File.separator, File.separator, File.separator);
    public static final String PROPERTY_KEY_IMAGE_VIEWER = "imageViewer";
    public static final String PROPERTY_KEY_PRINT = "print";

    @Autowired
    private MessageSource messageSource;

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService.addPacket(..))", returning = "packetId")
    public void afterAddingPacket(String packetId) throws Exception {
            //Create the qr as image
            BufferedImage image = createQRCodeImage(packetId);

            //Save the generated image
            File qrCode = saveImage(packetId, image);

            //Check how user likes to handle qr-codes
            //never ask to print and don't print, ask before print or print automatically
            switch (System.getProperty(PROPERTY_KEY_PRINT)){
                case "0": //NEVER
                    break;
                case "2": //PRINT
                    Desktop.getDesktop().print(qrCode);
                    break;
                case "1": //ASK
                default:
                    askForPrint(packetId, qrCode);
                    break;
            }

            //Show qr code in system image viewer if user wants this
            String showInImageViewer = System.getProperty(PROPERTY_KEY_IMAGE_VIEWER);
            if ("true".equals(showInImageViewer)) {
                Desktop.getDesktop().open(qrCode);
            }

    }

    private void askForPrint(String packetID, File qrCode) throws IOException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(ICON);
        alert.setTitle(messageSource.getMessage("askForPrint.title", null, LocaleContextHolder.getLocale()));
        alert.setHeaderText(messageSource.getMessage("askForPrint.header", new Object[]{packetID}, LocaleContextHolder.getLocale()));
        alert.setContentText(messageSource.getMessage("askForPrint.content", new Object[]{packetID}, LocaleContextHolder.getLocale()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Desktop.getDesktop().print(qrCode);
        }
        //Do nothing if OK is not clicked.
    }

    private File saveImage(String packetID, BufferedImage image) throws IOException {
        File dir = new File(LOCATION_TO_SAVE);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File f = new File(String.format("%s%s.%s", LOCATION_TO_SAVE, packetID, IMAGE_EXTENSION));
        if (!f.exists()) {
            f.createNewFile();
        }
        ImageIO.write(image, IMAGE_EXTENSION, f);
        return f;
    }

    private BufferedImage createQRCodeImage(String packetID) throws IOException {
        BufferedImage image = ImageIO.read(QRCode.from(packetID).withSize(250, 250).file());
        addText(packetID, image.getGraphics());
        return image;
    }

    private void addText(String packetID, Graphics g) {
        g.setFont(g.getFont().deriveFont(20f));
        g.setColor(Color.black);
        g.drawString(IMAGE_TEXT, 10, 20);
        g.drawString(packetID, 10, 240);
        g.dispose();
    }
}
