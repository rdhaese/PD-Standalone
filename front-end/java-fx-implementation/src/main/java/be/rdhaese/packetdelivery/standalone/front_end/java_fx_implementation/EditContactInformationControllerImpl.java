package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;


import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.standalone.front_end.interfaces.EditContactInformationController;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.alert.AlertTool;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.event.RemoveListItemAction;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.list_item.EmailAddressListItem;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.list_item.FaxNumberListItem;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.list_item.PhoneNumberListItem;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation.Validator;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 *
 * @author Robin D'Haese
 */
@Controller
public class EditContactInformationControllerImpl extends AbstractWithMenuAndStatusBarController implements EditContactInformationController {

    @Autowired
    private AlertTool alertTool;
    @FXML
    private TextField txtCompanyName;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtMailbox;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtPhoneNumberTitle;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ListView<PhoneNumberListItem> lvPhoneNumbers;
    @FXML
    private TextField txtFaxNumberTitle;
    @FXML
    private TextField txtFaxNumber;
    @FXML
    private ListView<FaxNumberListItem> lvFaxNumbers;
    @FXML
    private TextField txtEmailAddressTitle;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private ListView<EmailAddressListItem> lvEmailAdresses;
    @FXML
    private TextArea taAboutText;
    @FXML
    private Button btnAddPhoneNumber;
    @FXML
    private Button btnAddFaxNumber;
    @FXML
    private Button btnAddEmailAddress;
    @FXML
    private Button btnSave;

    private ContextMenu contextMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtCompanyName.requestFocus();
            }
        });

        ContactDetailsDTO contactDetailsDTO = null;
        try {
            contactDetailsDTO = contactInformationService.get();
        } catch (Exception e) {
            //Do nothing, exception should already be logged and handled by aspects
            //Throw a runtime exception so this exception is not swallowed if for some reason the aspects are not working
            throw new RuntimeException(e);
        }
        if (contactDetailsDTO != null) {
            txtCompanyName.setText(contactDetailsDTO.getCompanyName());
            taAboutText.setText(contactDetailsDTO.getAboutText());
            initializeAddress(contactDetailsDTO);
            initializePhoneNumbers(contactDetailsDTO);
            initializeFaxNumbers(contactDetailsDTO);
            initializeEmailAddresses(contactDetailsDTO);
        }
        super.initialize(location, resources);
    }

    private void initializeAddress(ContactDetailsDTO contactDetailsDTO) {
        txtStreet.setText(contactDetailsDTO.getStreet());
        txtNumber.setText(contactDetailsDTO.getNumber());
        txtMailbox.setText(contactDetailsDTO.getMailbox());
        txtCity.setText(contactDetailsDTO.getCity());
        txtPostalCode.setText(contactDetailsDTO.getPostalCode());
    }

    private void initializePhoneNumbers(ContactDetailsDTO contactDetailsDTO) {
        for (Map.Entry<String, String> phoneNumber : contactDetailsDTO.getPhoneNumbers().entrySet()) {
            PhoneNumberListItem phoneNumberListItem = new PhoneNumberListItem(messageSource);
            phoneNumberListItem.setPhoneNumberTitle(phoneNumber.getKey());
            phoneNumberListItem.setPhoneNumber(phoneNumber.getValue());
            lvPhoneNumbers.getItems().add(phoneNumberListItem);
        }
        addContextMenu(lvPhoneNumbers);
        addDeleteButtonListener(lvPhoneNumbers);
    }

    private void initializeFaxNumbers(ContactDetailsDTO contactDetailsDTO) {
        for (Map.Entry<String, String> faxNumber : contactDetailsDTO.getFaxNumbers().entrySet()) {
            FaxNumberListItem faxNumberListItem = new FaxNumberListItem(messageSource);
            faxNumberListItem.setFaxNumberTitle(faxNumber.getKey());
            faxNumberListItem.setFaxNumber(faxNumber.getValue());
            lvFaxNumbers.getItems().add(faxNumberListItem);
        }
        addContextMenu(lvFaxNumbers);
        addDeleteButtonListener(lvFaxNumbers);
    }

    private void initializeEmailAddresses(ContactDetailsDTO contactDetailsDTO) {
        for (Map.Entry<String, String> emailAddress : contactDetailsDTO.getEmailAddresses().entrySet()) {
            EmailAddressListItem emailAddressListItem = new EmailAddressListItem(messageSource);
            emailAddressListItem.setEmailAddressTitle(emailAddress.getKey());
            emailAddressListItem.setEmailAddress(emailAddress.getValue());
            lvEmailAdresses.getItems().add(emailAddressListItem);
        }
        addContextMenu(lvEmailAdresses);
        addDeleteButtonListener(lvEmailAdresses);
    }

    private void addContextMenu(ListView<?> listView) {
        MenuItem menuItem = new MenuItem(getMessage("menuItem.remove"));
        menuItem.setOnAction(new RemoveListItemAction(listView));
        contextMenu = new ContextMenu(menuItem);
        listView.setContextMenu(contextMenu);
    }

    private void addDeleteButtonListener(ListView<?> listView) {
        listView.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if ((KeyCode.DELETE.equals(event.getCode())) && (!listView.getSelectionModel().isEmpty())) {
                    listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
                }
            }
        });
    }

    public void save() throws Exception{
        ContactDetailsDTO contactDetailsDTO = new ContactDetailsDTO();
        if (validateInput(contactDetailsDTO)) {
            addPhoneNumbersBeforeSave(contactDetailsDTO);
            addFaxNumbersBeforeSave(contactDetailsDTO);
            addEmailAddressesBeforeSave(contactDetailsDTO);
            contactDetailsDTO.setAboutText(taAboutText.getText());
            if (contactInformationService.post(contactDetailsDTO)) {
                ((Stage) txtCompanyName.getScene().getWindow()).setTitle(contactDetailsDTO.getCompanyName());
                showOverview(txtCompanyName.getScene(), getMessage("toolbar.message.contactDetailsEditedSuccessful"));
            } else {
                markForError(btnSave);
                btnSave.getTooltip().setText(getMessage("contactInformation.button.save.error"));
            }
        }
    }

    private void addPhoneNumbersBeforeSave(ContactDetailsDTO contactDetailsDTO) {
        lvPhoneNumbers.getItems().forEach(x -> {
            contactDetailsDTO.addPhoneNumber(x.getPhoneNumberTitle(), x.getPhoneNumber());
        });
    }

    private void addFaxNumbersBeforeSave(ContactDetailsDTO contactDetailsDTO) {
        lvFaxNumbers.getItems().forEach(x -> {
            contactDetailsDTO.addFaxNumber(x.getFaxNumberTitle(), x.getFaxNumber());
        });
    }

    private void addEmailAddressesBeforeSave(ContactDetailsDTO contactDetailsDTO) {
        lvEmailAdresses.getItems().forEach(x -> {
            contactDetailsDTO.addEmailAddress(x.getEmailAddressTitle(), x.getEmailAddress());
        });
    }

    private boolean validateInput(ContactDetailsDTO contactDetailsDTO) {
        return validateCompanyName(contactDetailsDTO)
                & validateStreet(contactDetailsDTO)
                & validateNumber(contactDetailsDTO)
                & validateMailbox(contactDetailsDTO)
                & validateCity(contactDetailsDTO)
                & validatePostalCode(contactDetailsDTO);
    }

    private boolean validateCompanyName(ContactDetailsDTO contactDetailsDTO) {
        if (hasValidInput(txtCompanyName)) {
            contactDetailsDTO.setCompanyName(txtCompanyName.getText());
            removeErrorStyleIfNeeded(txtCompanyName);
            return true;
        }
        markForError(txtCompanyName, "contactInformation.tooltip.companyName");
        return false;
    }

    private boolean validateStreet(ContactDetailsDTO contactDetailsDTO) {
        if (validator.isValidStreet(txtStreet.getText())) {
            contactDetailsDTO.setStreet(txtStreet.getText());
            removeErrorStyleIfNeeded(txtStreet);
            return true;
        }
        markForError(txtStreet, "contactInformation.tooltip.street");
        return false;
    }

    private boolean validateNumber(ContactDetailsDTO contactDetailsDTO) {
        if (validator.isValidNumber(txtNumber.getText())) {
            contactDetailsDTO.setNumber(txtNumber.getText());
            removeErrorStyleIfNeeded(txtNumber);
            return true;
        }
        markForError(txtNumber, "contactInformation.tooltip.number");
        return false;
    }

    private boolean validateMailbox(ContactDetailsDTO contactDetailsDTO) {
        if (validator.isValidMailbox(txtMailbox.getText())) {
            contactDetailsDTO.setMailbox(txtMailbox.getText());
            removeErrorStyleIfNeeded(txtMailbox);
            return true;
        }
        markForError(txtMailbox, "contactInformation.tooltip.mailbox");
        return false;
    }

    private boolean validateCity(ContactDetailsDTO contactDetailsDTO) {
        if (validator.isValidCity(txtCity.getText())) {
            contactDetailsDTO.setCity(txtCity.getText());
            removeErrorStyleIfNeeded(txtCity);
            return true;
        }
        markForError(txtCity, "contactInformation.tooltip.city");
        return false;
    }

    private boolean validatePostalCode(ContactDetailsDTO contactDetailsDTO) {
        if (validator.isValidPostalCode(txtPostalCode.getText())) {
            contactDetailsDTO.setPostalCode(txtPostalCode.getText());
            removeErrorStyleIfNeeded(txtPostalCode);
            return true;
        }
        markForError(txtPostalCode, "contactInformation.tooltip.postalCode");
        return false;
    }

    public void cancel() {
        Optional<ButtonType> result = alertTool.getCancelAlert().showAndWait();

        if (result.get() == ButtonType.OK) {
            showOverview(txtCompanyName.getScene(), null);
        }
    }

    public void onPhoneNumberTitleTextfieldChanged() {
        PhoneNumberListItem phoneNumberListItem = new PhoneNumberListItem(messageSource);
        phoneNumberListItem.setPhoneNumberTitle(txtPhoneNumberTitle.getText());
        if (lvPhoneNumbers.getItems().contains(phoneNumberListItem)) {
            btnAddPhoneNumber.setText(getMessage("contactInformation.button.save"));
        } else {
            btnAddPhoneNumber.setText(getMessage("contactInformation.button.add"));
        }
    }

    public void addPhoneNumber() {
        PhoneNumberListItem phoneNumberListItem = new PhoneNumberListItem(messageSource);
        if (validateInput(phoneNumberListItem)) {
            int index;
            if ((index = lvPhoneNumbers.getItems().indexOf(phoneNumberListItem)) != -1) {
                lvPhoneNumbers.getItems().get(index).setPhoneNumber(txtPhoneNumber.getText());
                lvPhoneNumbers.refresh();
            } else {
                lvPhoneNumbers.getItems().add(phoneNumberListItem);
                txtPhoneNumberTitle.clear();
                txtPhoneNumber.clear();
            }
        }
    }

    private boolean validateInput(PhoneNumberListItem phoneNumberListItem) {
        return validatePhoneNumberTitle(phoneNumberListItem)
                & validatePhoneNumber(phoneNumberListItem);
    }

    private boolean validatePhoneNumberTitle(PhoneNumberListItem phoneNumberListItem) {
        if (hasValidInput(txtPhoneNumberTitle)) {
            phoneNumberListItem.setPhoneNumberTitle(txtPhoneNumberTitle.getText());
            removeErrorStyleIfNeeded(txtPhoneNumberTitle);
            return true;
        }
        markForError(txtPhoneNumberTitle, "contactInformation.tooltip.phoneNumbers.title");
        return false;
    }

    private boolean validatePhoneNumber(PhoneNumberListItem phoneNumberListItem) {
        if (validator.isValidPhoneNumber(txtPhoneNumber.getText())) {
            phoneNumberListItem.setPhoneNumber(txtPhoneNumber.getText());
            removeErrorStyleIfNeeded(txtPhoneNumber);
            return true;
        }
        markForError(txtPhoneNumber, "contactInformation.tooltip.phoneNumbers.phoneNumber");
        return false;
    }

    public void removePhoneNumber() {
        PhoneNumberListItem phoneNumberListItem;
        if ((phoneNumberListItem = lvPhoneNumbers.getSelectionModel().getSelectedItem()) != null) {
            lvPhoneNumbers.getItems().remove(phoneNumberListItem);
            btnAddPhoneNumber.setText(getMessage("contactInformation.button.add"));
        }
    }

    public void onPhoneNumbersClicked() {
        PhoneNumberListItem phoneNumberListItem;
        if ((phoneNumberListItem = lvPhoneNumbers.getSelectionModel().getSelectedItem()) != null) {
            txtPhoneNumberTitle.setText(phoneNumberListItem.getPhoneNumberTitle());
            txtPhoneNumber.setText(phoneNumberListItem.getPhoneNumber());
            btnAddPhoneNumber.setText(getMessage("contactInformation.button.save"));

        }
    }

    public void onFaxNumberTitleTextfieldChanged() {
        FaxNumberListItem faxNumberListItem = new FaxNumberListItem(messageSource);
        faxNumberListItem.setFaxNumberTitle(txtFaxNumberTitle.getText());
        if (lvFaxNumbers.getItems().contains(faxNumberListItem)) {
            btnAddFaxNumber.setText(getMessage("contactInformation.button.save"));
        } else {
            btnAddFaxNumber.setText(getMessage("contactInformation.button.add"));
        }
    }

    public void addFaxNumber() {
        FaxNumberListItem faxNumberListItem = new FaxNumberListItem(messageSource);
        if (validateInput(faxNumberListItem)) {
            int index;
            if ((index = lvFaxNumbers.getItems().indexOf(faxNumberListItem)) != -1) {
                lvFaxNumbers.getItems().get(index).setFaxNumber(txtFaxNumber.getText());
                lvFaxNumbers.refresh();
            } else {
                lvFaxNumbers.getItems().add(faxNumberListItem);
                txtFaxNumberTitle.clear();
                txtFaxNumber.clear();
            }
        }
    }

    private boolean validateInput(FaxNumberListItem faxNumberListItem) {
        return validateFaxNumberTitle(faxNumberListItem)
                & validateFaxNumber(faxNumberListItem);
    }

    private boolean validateFaxNumberTitle(FaxNumberListItem faxNumberListItem) {
        if (hasValidInput(txtFaxNumberTitle)) {
            faxNumberListItem.setFaxNumberTitle(txtFaxNumberTitle.getText());
            removeErrorStyleIfNeeded(txtFaxNumberTitle);
            return true;
        }
        markForError(txtFaxNumberTitle, "contactInformation.tooltip.faxNumbers.title");
        return false;
    }

    private boolean validateFaxNumber(FaxNumberListItem faxNumberListItem) {
        if (validator.isValidPhoneNumber(txtFaxNumber.getText())) {
            faxNumberListItem.setFaxNumber(txtFaxNumber.getText());
            removeErrorStyleIfNeeded(txtFaxNumber);
            return true;
        }
        markForError(txtFaxNumber, "contactInformation.tooltip.faxNumbers.faxNumber");
        return false;
    }

    public void removeFaxNumber() {
        FaxNumberListItem faxNumberListItem;
        if ((faxNumberListItem = lvFaxNumbers.getSelectionModel().getSelectedItem()) != null) {
            lvFaxNumbers.getItems().remove(faxNumberListItem);
            btnAddFaxNumber.setText(getMessage("contactInformation.button.add"));
        }
    }

    public void onFaxNumbersClicked() {
        FaxNumberListItem faxNumberListItem;
        if ((faxNumberListItem = lvFaxNumbers.getSelectionModel().getSelectedItem()) != null) {
            txtFaxNumberTitle.setText(faxNumberListItem.getFaxNumberTitle());
            txtFaxNumber.setText(faxNumberListItem.getFaxNumber());
            btnAddFaxNumber.setText(getMessage("contactInformation.button.save"));
        }
    }

    public void onEmailAddressTitleTextfieldChanged() {
        EmailAddressListItem emailAddressListItem = new EmailAddressListItem(messageSource);
        emailAddressListItem.setEmailAddressTitle(txtEmailAddressTitle.getText());
        if (lvEmailAdresses.getItems().contains(emailAddressListItem)) {
            btnAddEmailAddress.setText(getMessage("contactInformation.button.save"));
        } else {
            btnAddEmailAddress.setText(getMessage("contactInformation.button.add"));
        }
    }

    public void addEmailAddress() {
        EmailAddressListItem emailAddressListItem = new EmailAddressListItem(messageSource);
        if (validateInput(emailAddressListItem)) {
            int index;
            if ((index = lvEmailAdresses.getItems().indexOf(emailAddressListItem)) != -1) {
                lvEmailAdresses.getItems().get(index).setEmailAddress(txtEmailAddress.getText());
                lvEmailAdresses.refresh();
            } else {
                lvEmailAdresses.getItems().add(emailAddressListItem);
                txtEmailAddressTitle.clear();
                txtEmailAddress.clear();
            }
        }
    }

    private boolean validateInput(EmailAddressListItem emailAddressListItem) {
        return validateEmailAddressTitle(emailAddressListItem)
                & validateEmailAddress(emailAddressListItem);
    }

    private boolean validateEmailAddressTitle(EmailAddressListItem emailAddressListItem) {
        if (hasValidInput(txtEmailAddressTitle)) {
            emailAddressListItem.setEmailAddressTitle(txtEmailAddressTitle.getText());
            removeErrorStyleIfNeeded(txtEmailAddressTitle);
            return true;
        }
        markForError(txtEmailAddressTitle, "contactInformation.tooltip.emailAddresses.title");
        return false;
    }

    private boolean validateEmailAddress(EmailAddressListItem emailAddressListItem) {
        if (validator.isValidEmailAddress(txtEmailAddress.getText())) {
            emailAddressListItem.setEmailAddress(txtEmailAddress.getText());
            removeErrorStyleIfNeeded(txtEmailAddress);
            return true;
        }
        markForError(txtEmailAddress, "contactInformation.tooltip.emailAddresses.emailAddress");
        return false;
    }

    public void removeEmailAddress() {
        EmailAddressListItem emailAddressListItem;
        if ((emailAddressListItem = lvEmailAdresses.getSelectionModel().getSelectedItem()) != null) {
            lvEmailAdresses.getItems().remove(emailAddressListItem);
            btnAddEmailAddress.setText(getMessage("contactInformation.button.add"));
        }
    }

    public void onEmailAddressesClicked() {
        EmailAddressListItem emailAddressListItem;
        if ((emailAddressListItem = lvEmailAdresses.getSelectionModel().getSelectedItem()) != null) {
            txtEmailAddressTitle.setText(emailAddressListItem.getEmailAddressTitle());
            txtEmailAddress.setText(emailAddressListItem.getEmailAddress());
            btnAddEmailAddress.setText(getMessage("contactInformation.button.save"));
        }
    }
}
