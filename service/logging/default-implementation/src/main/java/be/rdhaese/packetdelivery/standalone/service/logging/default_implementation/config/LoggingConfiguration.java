package be.rdhaese.packetdelivery.standalone.service.logging.default_implementation.config;


import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LostPacketsWebService;
import be.rdhaese.packetdelivery.standalone.service.logging.default_implementation.AddPacketLoggerImpl;
import be.rdhaese.packetdelivery.standalone.service.logging.default_implementation.AuthenticationLoggerImpl;
import be.rdhaese.packetdelivery.standalone.service.logging.default_implementation.ContactInformationLoggerImpl;
import be.rdhaese.packetdelivery.standalone.service.logging.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created on 4/01/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
public class LoggingConfiguration {

    @Bean(name = "addPacketLogger")
    public Logger addPacketLogger(){
        return LoggerFactory.getLogger(AddPacketLogger.class);
    }

    @Bean(name = "authenticationLogger")
    public Logger authenticationLogger(){
        return LoggerFactory.getLogger(AuthenticationLogger.class);
    }

    @Bean(name = "contactInformationLogger")
    public Logger editContactInformationLogger(){
        return LoggerFactory.getLogger(ContactInformationLogger.class);
    }

    @Bean(name = "lostPacketsLogger")
    public Logger lostPacketsLogger(){
        return LoggerFactory.getLogger(LostPacketsLogger.class);
    }

    @Bean(name = "optionsLogger")
    public Logger optionsLogger(){
        return LoggerFactory.getLogger(OptionsLogger.class);
    }

    @Bean(name = "problematicPacketsLogger")
    public Logger problematicPacketsLogger(){
        return LoggerFactory.getLogger(ProblematicPacketsLogger.class);
    }

    @Bean(name = "regionsLogger")
    public Logger regionsLogger(){
        return LoggerFactory.getLogger(RegionsLogger.class);
    }
}
