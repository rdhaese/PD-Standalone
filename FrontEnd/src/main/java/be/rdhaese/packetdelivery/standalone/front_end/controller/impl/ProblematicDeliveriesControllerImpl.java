package be.rdhaese.packetdelivery.standalone.front_end.controller.impl;

import be.rdhaese.packetdelivery.dto.PacketDTO;
import be.rdhaese.packetdelivery.standalone.front_end.comparator.StringAsDateComparator;
import be.rdhaese.packetdelivery.standalone.front_end.controller.abstract_impl.AbstractWithMenuAndStatusBarController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.ProblematicDeliveriesController;
import be.rdhaese.packetdelivery.standalone.front_end.controller.ProblematicDeliveryController;
import be.rdhaese.packetdelivery.standalone.front_end.enums.FXMLS;
import be.rdhaese.packetdelivery.standalone.front_end.table_item.ProblematicPacketTableItem;
import be.rdhaese.packetdelivery.standalone.service.ProblematicPacketsService;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class ProblematicDeliveriesControllerImpl extends AbstractWithMenuAndStatusBarController implements ProblematicDeliveriesController {

    @Autowired
    private DateFormat dateFormat;
    @Autowired
    private ProblematicDeliveryController problematicDeliveryController;
    @Autowired
    private ProblematicPacketsService problematicPacketsService;

    @FXML
    private TableView<ProblematicPacketTableItem> tvProblematicPackets;
    @FXML
    private TableColumn<ProblematicPacketTableItem, String> tcPacketId;
    @FXML
    private TableColumn<ProblematicPacketTableItem, String> tcDateMarkedAsProblematic;
    @FXML
    private TableColumn<ProblematicPacketTableItem, String> tcClient;
    @FXML
    private TableColumn<ProblematicPacketTableItem, String> tcDelivery;

    @FXML
    private TextField txtIdFilter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTableColumns();
        FilteredList<ProblematicPacketTableItem> lostPackets = insertItemsFromBackEnd();
        bindIdFilter(lostPackets);
        showDetailsIfRowClicked();
        super.initialize(location, resources);
    }

    private void showDetailsIfRowClicked() {
        tvProblematicPackets.setRowFactory(rf -> {
            TableRow<ProblematicPacketTableItem> row = new TableRow<ProblematicPacketTableItem>();
            row.setCursor(Cursor.HAND);
            row.setOnMouseClicked(e -> {
                if (!tvProblematicPackets.getSelectionModel().isEmpty()) {
                    String packetId = tvProblematicPackets.getSelectionModel().getSelectedItem().getPacketId();
                    problematicDeliveryController.setCurrentPacket(packetId);
                    showScene(tvProblematicPackets.getScene(), FXMLS.PROBLEMATIC_DELIVERY);
                }
            });
            return row;
        } );
    }

    private void bindIdFilter(FilteredList<ProblematicPacketTableItem> lostPackets) {
        txtIdFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            lostPackets.setPredicate(problematicPacketTableItem -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (problematicPacketTableItem.getPacketId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    private FilteredList<ProblematicPacketTableItem> insertItemsFromBackEnd() {
        Collection<ProblematicPacketTableItem> problematicPacketTableItems = mapToTableItems(problematicPacketsService.getProblematicPackets());
        FilteredList<ProblematicPacketTableItem> filterableProblematicPacketTableItems = new FilteredList<>(FXCollections.observableArrayList(problematicPacketTableItems));
        SortedList<ProblematicPacketTableItem> problematicPacketTableItemSortedList = new SortedList<>(filterableProblematicPacketTableItems);
        problematicPacketTableItemSortedList.comparatorProperty().bind(tvProblematicPackets.comparatorProperty());
        tvProblematicPackets.setItems(problematicPacketTableItemSortedList);
        return filterableProblematicPacketTableItems;
    }

    private void initializeTableColumns() {
        tcPacketId.setCellValueFactory(f -> f.getValue().packetIdProperty());
        tcDateMarkedAsProblematic.setCellValueFactory(f -> f.getValue().dateMarkedAsProblematicProperty());
        tcDateMarkedAsProblematic.setComparator(new StringAsDateComparator(dateFormat));
        tcClient.setCellValueFactory(f -> f.getValue().clientProperty());
        tcDelivery.setCellValueFactory(f -> f.getValue().deliveryProperty());
        tvProblematicPackets.getSortOrder().add(tcDateMarkedAsProblematic);
    }

    private Collection<ProblematicPacketTableItem> mapToTableItems(Collection<PacketDTO> problematicPackets) {
        List<ProblematicPacketTableItem> problematicPacketTableItems = new ArrayList<>();
        for (PacketDTO problematicPacket : problematicPackets) {
            problematicPacketTableItems.add(mapToTableItem(problematicPacket));
        }
        return problematicPacketTableItems;
    }

    private ProblematicPacketTableItem mapToTableItem(PacketDTO problematicPacket) {
        ProblematicPacketTableItem problematicPacketTableItem = new ProblematicPacketTableItem();
        problematicPacketTableItem.setPacketId(problematicPacket.getPacketId());
        problematicPacketTableItem.setDateMarkedAsProblematic(dateFormat.format(problematicPacket.getStatusChangedOn()));
        problematicPacketTableItem.setClient(mapClient(problematicPacket));
        problematicPacketTableItem.setDelivery(mapDelivery(problematicPacket));
        return problematicPacketTableItem;
    }

    private String mapClient(PacketDTO problematicPacket) {
        return new StringBuilder(problematicPacket.getClientName())
                .append(System.lineSeparator())
                .append(problematicPacket.getClientPhone())
                .append(System.lineSeparator())
                .append(problematicPacket.getClientEmail())
                .toString();
    }

    private String mapDelivery(PacketDTO problematicPacket) {
        return new StringBuilder(problematicPacket.getDeliveryName())
                .append(System.lineSeparator())
                .append(problematicPacket.getDeliveryPhone())
                .append(System.lineSeparator())
                .append(problematicPacket.getDeliveryEmail())
                .toString();
    }

    @Override
    public void cancel() {
        showOverview(lblLoggedInUsername.getScene(), null);
    }
}
