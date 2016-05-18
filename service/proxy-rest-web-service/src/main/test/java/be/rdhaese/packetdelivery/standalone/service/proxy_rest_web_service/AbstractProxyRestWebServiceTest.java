package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service.properties.BackEndProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestConfig.class)
public class AbstractProxyRestWebServiceTest extends TestCase {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BackEndProperties backEndProperties;

    protected MockRestServiceServer server;

    @Before
    public void setUp(){
        server = MockRestServiceServer.createServer(restTemplate);
    }

    protected String getWithServerPath(String urlPart){
        return String.format("%s/%s", backEndProperties.getServerPath(), urlPart);
    }

    protected static byte[] convertObjectToJsonBytes(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
