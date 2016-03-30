package be.rdhaese.packetdelivery.standalone.front_end.aspects.default_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.aspects.interfaces.PacketAddedAspect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import net.glxn.qrgen.javase.QRCode;
import org.aspectj.lang.annotation.AfterReturning;
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
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class PacketAddedAspectImpl implements PacketAddedAspect {

    private static final String IMAGE_EXTENSION = "jpg";
    private static final String IMAGE_TEXT = "Packet ID";
    private static final String LOCATION_TO_SAVE = String.format("%s%spacket-delivery-system%scodes%s",
            System.getProperty("user.home"), File.separator, File.separator, File.separator);

    @Autowired
    private MessageSource messageSource;

    //TODO externalize Strings
    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.AddPacketWebService.addPacket(..))", returning = "packetId")
    public void afterAddingPacket(String packetId) {
        try {
            BufferedImage image = createQRCodeImage(packetId);
            File qrCode = saveImage(packetId, image);
            askForPrint(packetId, qrCode);
            //TODO in an options menu: option to set this off
            Desktop.getDesktop().open(qrCode); //TODO test generated QR code, scan with smartphone and check content
        } catch (IOException ioe) {
            //TODO log error
            ioe.printStackTrace();
        }
    }

    private void askForPrint(String packetID, File qrCode) throws IOException {
        //TODO in an options menu: configure that this always happens automatically or never asks
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
        File f = new File(String.format("%s%s.%s", LOCATION_TO_SAVE, packetID, IMAGE_EXTENSION)); //TODO, location on mac and linux? Should be known before presenting
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
