package be.rdhaese.packetdelivery.standalone.service.interfaces;

import be.rdhaese.packetdelivery.standalone.service.interfaces.util.UriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
public class AbstractService {

    @Autowired
    private UriUtil uris;

    protected UriUtil getUris() {
        return uris;
    }

    protected void setUris(UriUtil uris) {
        this.uris = uris;
    }

    protected RestTemplate getNewRestTemplate(){
        return new RestTemplate();
    }
}