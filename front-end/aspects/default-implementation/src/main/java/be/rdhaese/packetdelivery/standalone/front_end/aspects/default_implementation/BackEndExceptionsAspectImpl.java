package be.rdhaese.packetdelivery.standalone.front_end.aspects.default_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.aspects.interfaces.BackEndExceptionsAspect;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created on 18/04/2016.
 *
 * @author Robin D'Haese
 */
@Aspect
@Component
public class BackEndExceptionsAspectImpl implements BackEndExceptionsAspect {

    public static final Image ICON = new Image(PacketAddedAspectImpl.class.getResourceAsStream("/img/icon.png"));

    @Autowired
    private MessageSource messageSource;

    @Override
    @Around("execution(* be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.*.*(..))")
    public Object showDialogOnExceptionAndExit(ProceedingJoinPoint joinPoint) {
        Object value = null;
        try {
            value =  joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(ICON);
                    alert.setTitle(messageSource.getMessage("backendError.title", null, LocaleContextHolder.getLocale()));
                    alert.setHeaderText(messageSource.getMessage("backendError.header", null, LocaleContextHolder.getLocale()));
                    alert.setContentText(messageSource.getMessage("backendError.content", null, LocaleContextHolder.getLocale()));
                    alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                        @Override
                        public void handle(DialogEvent event) {
                            Platform.exit();
                        }
                    });
                    alert.show();
                }
            });
        }
        return value;
    }
}
