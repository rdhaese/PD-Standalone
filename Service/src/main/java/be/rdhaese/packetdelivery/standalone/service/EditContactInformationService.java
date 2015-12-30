package be.rdhaese.packetdelivery.standalone.service;


import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public interface EditContactInformationService {
    ContactDetailsDTO get();

    boolean save(ContactDetailsDTO contactDetailsDTO);
}
