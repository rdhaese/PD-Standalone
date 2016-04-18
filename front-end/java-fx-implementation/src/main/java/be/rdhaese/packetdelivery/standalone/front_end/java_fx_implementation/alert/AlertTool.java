package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert;

import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SplashPreLoader;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created on 18/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class AlertTool {

    @Autowired
    private MessageSource messageSource;

    public Alert getAlert(Alert.AlertType type, String title, String header, String content){
        Alert alert = new Alert(type);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(SplashPreLoader.ICON);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    public Alert getAlertUsingMessageSource(Alert.AlertType type, String titleKey, String headerKey, String contentKey){
        return getAlert(
                type,
                messageSource.getMessage(titleKey, null, LocaleContextHolder.getLocale()),
                messageSource.getMessage(headerKey, null, LocaleContextHolder.getLocale()),
                messageSource.getMessage(contentKey, null, LocaleContextHolder.getLocale()));
    }
}
