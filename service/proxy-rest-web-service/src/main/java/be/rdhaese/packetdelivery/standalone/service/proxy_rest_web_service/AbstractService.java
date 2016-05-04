package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service.properties.BackEndProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
public class AbstractService {

    @Autowired
    private BackEndProperties backEndProperties;

    @Autowired
    private RestTemplate restTemplate;

    protected RestTemplate getRestTemplate(){
        return restTemplate;
    }

    protected BackEndProperties getBackEndProperties() {
        return backEndProperties;
    }
}
