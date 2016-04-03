package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.dto.PacketDTO;
<<<<<<< HEAD:front-end/java-fx-implementation/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/java_fx_implementation/LostPacketsControllerImpl.java
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.LostPacketsController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.comparator.StringAsDateComparator;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.table_item.LostPacketTableItem;
=======
import be.rdhaese.packetdelivery.standalone.front_end.comparator.StringAsDateComparator;
import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.LostPacketsController;
import be.rdhaese.packetdelivery.standalone.front_end.table_item.LostPacketTableItem;
import be.rdhaese.packetdelivery.standalone.service.LostPacketsService;
>>>>>>> 7362763c2cab397bd2f065faa67fbe81a313c24b:FrontEnd/src/main/java/be/rdhaese/packetdelivery/standalone/front_end/controller/impl/LostPacketsControllerImpl.java
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        FilteredList<LostPacketTableItem> lostPackets = insertItemsFromBackEnd();
        bindIdFilter(lostPackets);
        super.initialize(location, resources);
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
        tcDateMarkedAsLost.setComparator(new StringAsDateComparator(dateFormat));
        tcClient.setCellValueFactory(f -> f.getValue().clientProperty());
        tcDelivery.setCellValueFactory(f -> f.getValue().deliveryProperty());
        tcFound.setCellValueFactory(f -> f.getValue().foundProperty());
        tcFound.setCellFactory(tc -> new CheckBoxTableCell<>());
        tcRemove.setCellValueFactory(f -> f.getValue().removeProperty());
        tcRemove.setCellFactory(tc -> new CheckBoxTableCell<>());
        tvLostPackets.getSortOrder().add(tcDateMarkedAsLost);
    }

    private Collection<LostPacketTableItem> mapToTableItems(Collection<PacketDTO> lostPackets) {
        List<LostPacketTableItem> lostPacketTableItems = new ArrayList<>();
        for (PacketDTO lostPacket : lostPackets){
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
        //TODO ask if user is sure
        showOverview(lblLoggedInUsername.getScene(), null);
    }

    @Override
    public void save() {
        //TODO perform selected actions
        List<String> foundPackets = new ArrayList<>();
        List<String> removedPackets = new ArrayList<>();
        for (LostPacketTableItem lostPacketTableItem : tvLostPackets.getItems()){
            if (lostPacketTableItem.getFound() == true){
                foundPackets.add(lostPacketTableItem.getPacketId());
            } else if (lostPacketTableItem.getRemove() == true){
                removedPackets.add(lostPacketTableItem.getPacketId());
            }
        }
        if (foundPackets.size() > 0){
            lostPacketsService.markAsFound(foundPackets);
        }
        if (removedPackets.size() > 0){
            lostPacketsService.removeFromSystem(removedPackets);
        }
        showOverview(lblLoggedInUsername.getScene(), getMessage("toolbar.message.lostPacketsActionsPerformed", new Object[]{foundPackets.size(), removedPackets.size()}));
    }
}
