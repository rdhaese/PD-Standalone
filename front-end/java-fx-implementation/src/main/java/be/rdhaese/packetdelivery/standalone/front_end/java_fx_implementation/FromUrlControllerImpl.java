package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

<<<<<<< HEAD:front-end/java-fx-implementation/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/java_fx_implementation/FromUrlControllerImpl.java
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.FromUrlController;
=======
import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.FromUrlController;
>>>>>>> 7362763c2cab397bd2f065faa67fbe81a313c24b:FrontEnd/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/controller/impl/FromUrlControllerImpl.java
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
        if (!isEmpty(txtUrl)){
            //TODO get information from standalone_service
            //TODO map to form in other scene
        } else {
            //TODO mark field for error
        }
        //Do nothing
    }

    public void cancel(){
        Stage stage = (Stage) txtUrl.getScene().getWindow();
        stage.close();
    }
}
