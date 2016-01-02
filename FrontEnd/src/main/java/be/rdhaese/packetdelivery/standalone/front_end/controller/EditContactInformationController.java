package be.rdhaese.packetdelivery.standalone.front_end.controller;

/**
 * Created on 2/01/2016.
 *
 * @author Robin D'Haese
 */
public interface EditContactInformationController extends CancelableController {

    void save();

    void onPhoneNumberTitleTextfieldChanged();

    void addPhoneNumber();

    void removePhoneNumber();

    void onPhoneNumbersClicked();

    void onFaxNumberTitleTextfieldChanged();

    void addFaxNumber();

    void removeFaxNumber();

    void onFaxNumbersClicked();

    void onEmailAddressTitleTextfieldChanged();

    void addEmailAddress();

    void removeEmailAddress();

    void onEmailAddressesClicked();
}
