package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.standalone.service.AbstractService;
import be.rdhaese.packetdelivery.standalone.service.ContactInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class ContactInformationServiceImpl extends AbstractService implements ContactInformationService {

    public ContactDetailsDTO get() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ContactDetailsDTO> response = restTemplate.getForEntity(getUris().getContactInformationPath(), ContactDetailsDTO.class);
        return response.getBody();
    }

    public boolean save(ContactDetailsDTO contactDetailsDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(getUris().getSaveContactInformationPath(), contactDetailsDTO, boolean.class);
    }

    @Override
    public String getCompanyName() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUris().getCompanyNamePath(), String.class);
    }
}
