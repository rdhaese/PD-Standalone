package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.interfaces.FromUrlController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

/**
 * Created on 24/12/2015.
 *
 * @author Robin D'Haese
 */
@Controller
public class FromUrlControllerImpl extends AbstractController implements FromUrlController {

    @FXML
    private TextField txtUrl;


    public void loadInformation(){
        if (validator.isValidWebServiceUrl(txtUrl.getText())){
            removeErrorStyleIfNeeded(txtUrl);
            //TODO get information from standalone_service
            //TODO map to form in other scene
        } else {
            markForError(txtUrl, "fromUrl.tooltip");
        }
    }

    public void cancel(){
        Stage stage = (Stage) txtUrl.getScene().getWindow();
        stage.close();
    }
}
