package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SplashPreLoader;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.loader.SpringFxmlLoader;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.toolbar.ToolbarMessageHolder;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation.Validator;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 *
 * @author Robin D'Haese
 */
public abstract class AbstractController {
    private static final String TEXTFIELD_ERROR_STYLE_CLASS = "tferror";

    @Autowired
    private SpringFxmlLoader loader;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected Validator validator;
    @Autowired
    protected ToolbarMessageHolder messageHolder;

    private static FXMLS lastScene = null;

    protected String getMessage(String key, Object[] objects){
        return messageSource.getMessage(key, objects, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String key){
        return getMessage(key, null);
    }

    protected void showScene(Scene oldScene, FXMLS newScene){
        lastScene = newScene;
        Stage stage = (Stage) oldScene.getWindow();
        Parent parent = (Parent) loader.load(newScene.toString());
        Scene scene = new Scene(parent, oldScene.getWidth(), oldScene.getHeight());
        stage.setScene(scene);

    }

    protected void showScene(Scene oldScene, FXMLS newScene, String message){
        messageHolder.setMessage(message);
        showScene(oldScene, newScene);
    }

    protected void showOverview(Scene oldScene, String message) {
       showScene(oldScene, FXMLS.OVERVIEW, message);
    }

    protected void showInNewWindow(FXMLS scene, String titleMessageSourceKey, int width, int height, boolean resizable){
        Parent root = (Parent) loader.load(scene.toString());
        Stage stage = new Stage();
        stage.getIcons().add(SplashPreLoader.ICON);
        stage.setTitle(getMessage(titleMessageSourceKey));
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(resizable);
        stage.show();
    }

    protected void updateLastScene(Scene oldScene){
        showScene(oldScene, lastScene);
    }

    protected boolean hasValidInput(TextInputControl textInputControl) {
        if (textInputControl == null){
            return true;
        }
        return validator.isValidInput(textInputControl.getText());
    }

    protected void markForError(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (!styleClass.contains(TEXTFIELD_ERROR_STYLE_CLASS)) {
            styleClass.add(TEXTFIELD_ERROR_STYLE_CLASS);
        }
    }

    protected void markForError(Control control, String errorMessageKey){
        markForError(control);
        control.setTooltip(new Tooltip(getMessage(errorMessageKey)));
    }

    protected void removeErrorStyleIfNeeded(Control control) {
        ObservableList<String> styleClass = control.getStyleClass();
        if (styleClass.contains(TEXTFIELD_ERROR_STYLE_CLASS)) {
            styleClass.remove(TEXTFIELD_ERROR_STYLE_CLASS);
        }
        control.setTooltip(null);
    }
}
