package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.dto.PacketInformationDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.FromUrlController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.from_url.FromUrlResultHolder;
import be.rdhaese.packetdelivery.standalone.service.interfaces.PacketInformationWebService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class FromUrlControllerImpl extends AbstractController implements FromUrlController {

    @Autowired
    private PacketInformationWebService packetInformationWebService;
    @Autowired
    private FromUrlResultHolder fromUrlResultHolder;

    @FXML
    private TextField txtUrl;

    public void loadInformation(){
        if (validator.isValidWebServiceUrl(txtUrl.getText())){
            removeErrorStyleIfNeeded(txtUrl);
            PacketInformationDTO packetInformationDTO = packetInformationWebService.getPacketInformation(txtUrl.getText());
            if (packetInformationDTO == null){
                markForError(txtUrl, "fromUrl.tooltip.noResponse");
            } else {
                fromUrlResultHolder.setClientName(packetInformationDTO.getClientName());
                fromUrlResultHolder.setClientPhone(packetInformationDTO.getClientPhone());
                fromUrlResultHolder.setClientEmail(packetInformationDTO.getClientEmail());
                fromUrlResultHolder.setClientStreet(packetInformationDTO.getClientStreet());
                fromUrlResultHolder.setClientNumber(packetInformationDTO.getClientNumber());
                fromUrlResultHolder.setClientMailbox(packetInformationDTO.getClientMailbox());
                fromUrlResultHolder.setClientPostalCode(packetInformationDTO.getClientPostalCode());
                fromUrlResultHolder.setClientCity(packetInformationDTO.getClientCity());

                fromUrlResultHolder.setDeliveryName(packetInformationDTO.getDeliveryName());
                fromUrlResultHolder.setDeliveryPhone(packetInformationDTO.getDeliveryPhone());
                fromUrlResultHolder.setDeliveryEmail(packetInformationDTO.getDeliveryEmail());
                fromUrlResultHolder.setDeliveryStreet(packetInformationDTO.getDeliveryStreet());
                fromUrlResultHolder.setDeliveryNumber(packetInformationDTO.getDeliveryNumber());
                fromUrlResultHolder.setDeliveryMailbox(packetInformationDTO.getDeliveryMailbox());
                fromUrlResultHolder.setDeliveryPostalCode(packetInformationDTO.getDeliveryPostalCode());
                fromUrlResultHolder.setDeliveryCity(packetInformationDTO.getDeliveryCity());

                //Window can close
                cancel();
            }
        } else {
            markForError(txtUrl, "fromUrl.tooltip");
        }
    }

    public void cancel(){
        Stage stage = (Stage) txtUrl.getScene().getWindow();
        stage.close();
    }
}
