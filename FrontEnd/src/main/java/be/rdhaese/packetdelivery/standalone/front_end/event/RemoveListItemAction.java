package be.rdhaese.packetdelivery.standalone.front_end.event;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.awt.*;


/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
public class RemoveListItemAction<T> implements EventHandler<ActionEvent> {

    private ListView<T> lv;

    public RemoveListItemAction(ListView<T> lv){
        this.lv = lv;
    }

    @Override
    public void handle(ActionEvent event) {
        T li = lv.getSelectionModel().getSelectedItem();
        if (li != null){
            lv.getItems().remove(li);
        }
    }
}
