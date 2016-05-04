package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.springframework.stereotype.Service;

/**
 * Created on 30/12/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class ContactInformationProxyRestWebService extends AbstractService implements ContactInformationWebService {

    public ContactDetailsDTO get() {
        return getRestTemplate().getForEntity(getBackEndProperties().getUris().getContactInformation(), ContactDetailsDTO.class).getBody();
    }

    @Override
    public boolean post(ContactDetailsDTO contactDetailsDTO) {
        return getRestTemplate().postForObject(getBackEndProperties().getUris().getSaveContactInformation(), contactDetailsDTO, boolean.class);
    }

    @Override
    public String getCompanyName() {
        return getRestTemplate().getForObject(getBackEndProperties().getUris().getCompanyName(), String.class);
    }
}
