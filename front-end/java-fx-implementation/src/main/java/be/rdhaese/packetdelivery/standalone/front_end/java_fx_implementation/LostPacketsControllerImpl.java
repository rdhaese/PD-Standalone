package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.LostPacketsController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert.AlertTool;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.comparator.dateAsStringComparator;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.table_item.LostPacketTableItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.*;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class LostPacketsControllerImpl extends AbstractWithMenuAndStatusBarController implements LostPacketsController {

    @Autowired
    private DateFormat dateFormat;
    @Autowired
    private LostPacketsWebService lostPacketsService;
    @Autowired
    private AlertTool alertTool;

    @FXML
    private TableView<LostPacketTableItem> tvLostPackets;
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
    @FXML
    private TextField txtIdFilter;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtIdFilter.requestFocus();
            }
        });

        initializeTableColumns();
        FilteredList<LostPacketTableItem> lostPackets = insertItemsFromBackEnd();
        bindIdFilter(lostPackets);

        btnRefresh.getParent().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (KeyCode.F5.equals(event.getCode())) {
                    btnRefresh.fire();
                }
            }
        });


        btnSave.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (KeyCode.SHIFT.equals(event.getCode())){
                    tvLostPackets.requestFocus();
                }
            }
        });

        tvLostPackets.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (KeyCode.F5.equals(event.getCode())) {
                    btnRefresh.fire();
                } else if ((KeyCode.R.equals(event.getCode())) && (!tvLostPackets.getSelectionModel().isEmpty())) {
                    LostPacketTableItem selectedItem = tvLostPackets.getSelectionModel().getSelectedItem();
                    if (selectedItem.getRemove()) {
                        selectedItem.setRemove(false);
                    } else {
                        selectedItem.setRemove(true);
                    }
                } else if ((KeyCode.F.equals(event.getCode())) && (!tvLostPackets.getSelectionModel().isEmpty())) {
                    LostPacketTableItem selectedItem = tvLostPackets.getSelectionModel().getSelectedItem();
                    if (selectedItem.getFound()) {
                        selectedItem.setFound(false);
                    } else {
                        selectedItem.setFound(true);
                    }
                }
            }
        });
    }

    private void bindIdFilter(FilteredList<LostPacketTableItem> lostPackets) {
        txtIdFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            lostPackets.setPredicate(lostPacketTableItem -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (lostPacketTableItem.getPacketId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    private FilteredList<LostPacketTableItem> insertItemsFromBackEnd() {
        Collection<LostPacketTableItem> lostPacketTableItems = mapToTableItems(lostPacketsService.getLostPackets());
        FilteredList<LostPacketTableItem> filterableLostPacketTableItems = new FilteredList<>(FXCollections.observableArrayList(lostPacketTableItems));
        SortedList<LostPacketTableItem> lostPacketTableItemSortedList = new SortedList<LostPacketTableItem>(filterableLostPacketTableItems);
        lostPacketTableItemSortedList.comparatorProperty().bind(tvLostPackets.comparatorProperty());
        tvLostPackets.setItems(lostPacketTableItemSortedList);
        return filterableLostPacketTableItems;
    }

    private void initializeTableColumns() {
        tcPacketId.setCellValueFactory(f -> f.getValue().packetIdProperty());
        tcDateMarkedAsLost.setCellValueFactory(f -> f.getValue().dateMarkedAsLostProperty());
        tcDateMarkedAsLost.setComparator(new dateAsStringComparator(dateFormat));
        tcClient.setCellValueFactory(f -> f.getValue().clientProperty());
        tcDelivery.setCellValueFactory(f -> f.getValue().deliveryProperty());
        tcFound.setCellValueFactory(f -> f.getValue().foundProperty());
        tcFound.setCellFactory(tc -> new NonFocusableCheckBoxTableCell<>());
        tcRemove.setCellValueFactory(f -> f.getValue().removeProperty());
        tcRemove.setCellFactory(tc -> new NonFocusableCheckBoxTableCell<>());
        tvLostPackets.getSortOrder().add(tcDateMarkedAsLost);
    }

    private class NonFocusableCheckBoxTableCell<S, T> extends CheckBoxTableCell<S, T> {
        public NonFocusableCheckBoxTableCell(){
            //Does the trick in most cases, but not in the use case we are using the cell in.
            setFocusTraversable(false);
            //So add a key listener that acts when the tab key is released
            //This works because the checkbox got focus from the table when the tab key is pressed
            //The focus on the checkbox is visible for a split second, but the best I can do in a small amount of time
            //When the tab key is pressed, set focus to btnSave
            setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (KeyCode.TAB.equals(event.getCode())) {
                        btnSave.requestFocus();
                    }
                }
            });
        }
    }

    private Collection<LostPacketTableItem> mapToTableItems(Collection<PacketDTO> lostPackets) {
        List<LostPacketTableItem> lostPacketTableItems = new ArrayList<>();
        for (PacketDTO lostPacket : lostPackets) {
            lostPacketTableItems.add(mapToTableItem(lostPacket));
        }
        return lostPacketTableItems;
    }

    private LostPacketTableItem mapToTableItem(PacketDTO lostPacket) {
        LostPacketTableItem lostPacketTableItem = new LostPacketTableItem();
        lostPacketTableItem.setPacketId(lostPacket.getPacketId());
        lostPacketTableItem.setDateMarkedAsLost(dateFormat.format(lostPacket.getStatusChangedOn()));
        lostPacketTableItem.setClient(mapClient(lostPacket));
        lostPacketTableItem.setDelivery(mapDelivery(lostPacket));
        return lostPacketTableItem;
    }

    private String mapClient(PacketDTO lostPacket) {
        return new StringBuilder(lostPacket.getClientName())
                .append(System.lineSeparator())
                .append(lostPacket.getClientPhone())
                .append(System.lineSeparator())
                .append(lostPacket.getClientEmail())
                .toString();
    }

    private String mapDelivery(PacketDTO lostPacket) {
        return new StringBuilder(lostPacket.getDeliveryName())
                .append(System.lineSeparator())
                .append(lostPacket.getDeliveryPhone())
                .append(System.lineSeparator())
                .append(lostPacket.getDeliveryEmail())
                .toString();
    }

    @Override
    public void cancel() {
        Optional<ButtonType> result = alertTool.getCancelAlert().showAndWait();

        if (result.get() == ButtonType.OK) {
            showOverview(lblLoggedInUsername.getScene(), null);
        }
    }

    @Override
    public void save() {
        List<String> foundPackets = new ArrayList<>();
        List<String> removedPackets = new ArrayList<>();
        for (LostPacketTableItem lostPacketTableItem : tvLostPackets.getItems()) {
            if (lostPacketTableItem.getFound() == true) {
                foundPackets.add(lostPacketTableItem.getPacketId());
            } else if (lostPacketTableItem.getRemove() == true) {
                removedPackets.add(lostPacketTableItem.getPacketId());
            }
        }
        if (foundPackets.size() > 0) {
            lostPacketsService.markAsFound(foundPackets);
        }
        if (removedPackets.size() > 0) {
            lostPacketsService.removeFromSystem(removedPackets);
        }
        showOverview(lblLoggedInUsername.getScene(), getMessage("toolbar.message.lostPacketsActionsPerformed", new Object[]{foundPackets.size(), removedPackets.size()}));
    }

    @Override
    public void refresh() {
        FilteredList<LostPacketTableItem> lostPackets = insertItemsFromBackEnd();
        bindIdFilter(lostPackets);
    }
}
