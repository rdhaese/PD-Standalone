package be.rdhaese.packetdelivery.standalone.service.Impl;

import be.rdhaese.packetdelivery.standalone.service.util.UriUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
public class AbstractService {

    @Autowired
    private UriUtil uris;

    public UriUtil getUris() {
        return uris;
    }

    public void setUris(UriUtil uris) {
        this.uris = uris;
    }
}
