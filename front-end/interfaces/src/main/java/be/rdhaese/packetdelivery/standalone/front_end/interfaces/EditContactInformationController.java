package be.rdhaese.packetdelivery.standalone.front_end.interfaces;

/**
 *
 * @author Robin D'Haese
 */
public interface EditContactInformationController extends SaveableController, CancelableController, UpdatableController {

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
