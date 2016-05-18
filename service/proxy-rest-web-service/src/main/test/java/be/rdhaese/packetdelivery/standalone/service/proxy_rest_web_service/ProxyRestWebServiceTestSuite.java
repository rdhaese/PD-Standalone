package be.rdhaese.packetdelivery.standalone.service.proxy_rest_web_service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddPacketProxyRestWebServiceTest.class,
        AuthenticationProxyRestWebServiceExtendedTest.class,
        ContactInformationProxyRestWebServiceTest.class,
        LostPacketProxyRestWebServiceTest.class,
        OptionsProxyRestWebServiceTest.class,
        PacketInformationProxyRestWebServiceTest.class,
        ProblematicPacketsProxyRestWebServiceTest.class,
        RegionsProxyRestWebServiceTest.class
})
public class ProxyRestWebServiceTestSuite {
}
