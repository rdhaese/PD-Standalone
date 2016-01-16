package be.rdhaese.packetdelivery.standalone.front_end.controller;

/**
 * Created on 14/01/2016.
 *
 * @author Robin D'Haese
 */
public interface ProblematicDeliveryController extends CancelableController{

    void editAddress();
    void reSend();
    void returnToSender();
}
