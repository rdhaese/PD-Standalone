package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.OptionsWebService;
import be.rdhaese.packetdelivery.dto.OptionsDTO;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class OptionsProxyRestWebService extends AbstractService implements OptionsWebService {

    @Override
    public OptionsDTO getFor(String username) {
        return getRestTemplate().getForObject(getBackEndProperties().getUris().getOptions(), OptionsDTO.class, username);
    }

    @Override
    public Boolean save(OptionsDTO optionsDTO) {
        return getRestTemplate().postForObject(getBackEndProperties().getUris().getOptionsSave(), optionsDTO, Boolean.class);
    }
}
