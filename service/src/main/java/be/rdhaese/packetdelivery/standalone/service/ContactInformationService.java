package be.rdhaese.packetdelivery.standalone.service;


import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;

/**
 * Created on 27/12/2015.
 *
 * @author Robin D'Haese
 */
public interface ContactInformationService {
    ContactDetailsDTO get();

    boolean save(ContactDetailsDTO contactDetailsDTO);

    String getCompanyName();
}
