package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.RegionsWebService;
import be.rdhaese.packetdelivery.dto.RegionDTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */

public class RegionsProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private RegionsWebService regionsWebService;
    
    @Test
    public void testRegions() throws Exception {
        RegionDTO regionDto1 = new RegionDTO("nl1", "fr1", "de1", "en1", "CODE1");
        RegionDTO regionDto2 = new RegionDTO("nl2", "fr2", "de2", "en2", "CODE2");
        Collection<RegionDTO> regionDTOs = Arrays.asList(regionDto1, regionDto2);

        server.expect(requestTo(getWithServerPath("regions/all")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(regionDTOs), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(2, regionsWebService.regions().size());
    }
}
