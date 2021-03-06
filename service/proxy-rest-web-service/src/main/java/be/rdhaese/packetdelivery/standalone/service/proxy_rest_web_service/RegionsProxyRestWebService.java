package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
@Service
public class RegionsProxyRestWebService extends AbstractService implements RegionsWebService {

    @Override
    public Collection<RegionDTO> regions() {
        ResponseEntity<RegionDTO[]> response = getRestTemplate().getForEntity(getBackEndProperties().getUris().getAllRegions(), RegionDTO[].class);
       return Arrays.asList(response.getBody());
    }
}
