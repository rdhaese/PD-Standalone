package be.rdhaese.packetdelivery.standalone.front_end.aspect;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import net.glxn.qrgen.javase.QRCode;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@Aspect
public class PacketAddedAspect {

    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.standalone.service.AddPacketService.addPacket(..))", returning = "packetId")
    public void afterAddingPacket(String packetId){
        try {
            BufferedImage image = createQRCodeImage(packetId);
            File qrCode = saveImage(packetId, image);
            askForPrint(packetId, qrCode);
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
}
