package be.rdhaese.packetdelivery.standalone.front_end.controller;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.impl.OverviewControllerImpl;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created on 13/01/2016.
 *
 * @author Robin D'Haese
 */
public abstract class AbstractController {
    private static final String TEXTFIELD_ERROR_STYLE_CLASS = "tferror";

    @Autowired
    protected MessageSource messageSource;

    protected String getMessage(String key, Object[] objects){
        return messageSource.getMessage(key, objects, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String key){
        return getMessage(key, null);
    }

    protected void showScene(Scene oldScene, FXMLS newScene){
        Stage stage = (Stage) oldScene.getWindow();
        Parent parent = (Parent) App.LOADER.load(newScene.toString());
        Scene scene = new Scene(parent, 1024, 768);
        stage.setScene(scene);

    }
    protected void showOverview(Scene oldScene, String message) {
        if (message != null) {
            OverviewControllerImpl.setMessage(message);
        }
       showScene(oldScene, FXMLS.OVERVIEW);
    }

    protected void showInNewWindow(FXMLS scene, String titleMessageSourceKey, int width, int height, boolean resizable){
        Parent root = (Parent) App.LOADER.load(scene.toString());
        Stage stage = new Stage();
        stage.getIcons().add(App.ICON);
        stage.setTitle(getMessage(titleMessageSourceKey));
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(resizable);
        stage.show();
    }

    protected boolean isEmpty(TextInputControl textInputControl) {
        if (textInputControl == null){
            return true;
        }
        return textInputControl.getText().trim().isEmpty();
    }

    protected void markForError(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (!styleClass.contains(TEXTFIELD_ERROR_STYLE_CLASS)) {
            styleClass.add(TEXTFIELD_ERROR_STYLE_CLASS);
        }
    }

    protected void removeErrorStyleIfNeeded(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (styleClass.contains(TEXTFIELD_ERROR_STYLE_CLASS)) {
            styleClass.remove(TEXTFIELD_ERROR_STYLE_CLASS);
        }
    }
}
