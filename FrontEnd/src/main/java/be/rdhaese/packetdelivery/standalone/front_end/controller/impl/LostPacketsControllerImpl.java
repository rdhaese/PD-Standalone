package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.standalone.front_end.App;
import be.rdhaese.packetdelivery.standalone.front_end.controller.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.LostPacketsController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.front_end.table_item.LostPacketTableItem;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class LostPacketsControllerImpl extends AbstractWithMenuAndStatusBarController implements LostPacketsController {


    @FXML
    private TableView tvLostPackets;
    @FXML
    private TableColumn<LostPacketTableItem, String> tcPacketId;
    @FXML
    private TableColumn<LostPacketTableItem, String> tcDateMarkedAsLost;
    @FXML
    private TableColumn<LostPacketTableItem, String> tcClient;
    @FXML
    private TableColumn<LostPacketTableItem, String> tcDelivery;
    @FXML
    private TableColumn<LostPacketTableItem, Boolean> tcFound;
    @FXML
    private TableColumn<LostPacketTableItem, Boolean> tcRemove;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO binnenkrijgen van lijst van DTO's uit backend, mappen, inladen
        //TODO wanneer een checkbox geselecteerd word, de andere uitvinken
        //TODO filtering on packetid
        LostPacketTableItem lostPacketTableItem = new LostPacketTableItem("pakketID", "12/03/2016", "test\nclient\nbla", "test\ndelivery\nblablablablablablabla", false,false);
        ObservableList<LostPacketTableItem> lostPackets = FXCollections.observableArrayList(lostPacketTableItem);
        tcPacketId.setCellValueFactory( f -> f.getValue().packetIdProperty());
        tcDateMarkedAsLost.setCellValueFactory(f -> f.getValue().dateMarkedAsLostProperty());
        tcClient.setCellValueFactory(f -> f.getValue().clientProperty());
        tcDelivery.setCellValueFactory(f -> f.getValue().deliveryProperty());
        tcFound.setCellValueFactory(f -> f.getValue().foundProperty());
        tcFound.setCellFactory(tc -> new CheckBoxTableCell<>());
        tcRemove.setCellValueFactory(f -> f.getValue().removeProperty());
        tcRemove.setCellFactory(tc -> new CheckBoxTableCell<>());
        tvLostPackets.setItems(lostPackets);
        super.initialize(location, resources);
    }

    @Override
    public void cancel() {
        Stage stage = (Stage) lblLoggedInUsername.getScene().getWindow();
        Parent root = (Parent) App.LOADER.load(FXMLS.OVERVIEW.toString());
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    @Override
    public void save() {

    }
}
