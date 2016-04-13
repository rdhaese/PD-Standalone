package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.ContactInformationWebService;
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
        return getNewRestTemplate().getForEntity(getUris().getContactInformationPath(), ContactDetailsDTO.class).getBody();
    }

    @Override
    public boolean post(ContactDetailsDTO contactDetailsDTO) {
        return getNewRestTemplate().postForObject(getUris().getSaveContactInformationPath(), contactDetailsDTO, boolean.class);
    }

    @Override
    public String getCompanyName() {
        return getNewRestTemplate().getForObject(getUris().getCompanyNamePath(), String.class);
    }
}
