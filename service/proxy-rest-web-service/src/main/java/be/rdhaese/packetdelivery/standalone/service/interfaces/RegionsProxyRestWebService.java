package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 28/03/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class RegionsProxyRestWebService extends AbstractService implements RegionsWebService {
    @Override
    public Collection<RegionDTO> regions() {
        ResponseEntity<RegionDTO[]> response = getNewRestTemplate().getForEntity(getUris().getAllRegionsPath(), RegionDTO[].class);
       return Arrays.asList(response.getBody());
    }
}
