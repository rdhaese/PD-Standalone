package be.rdhaese.packetdelivery.standalone.front_end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created on 9/01/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
public class FrontEndConfig {

    private static final String CLASSPATH = "classpath:";
    private static final String MESSAGES_LOCATION= "locale/messages";
    private static final String ENCODING = "UTF-8";

    //used by spring
    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
        rrbms.setBasename(String.format("%s%s", CLASSPATH, MESSAGES_LOCATION));
        rrbms.setDefaultEncoding(ENCODING);
        rrbms.setUseCodeAsDefaultMessage(true);
        return rrbms;
    }

    //used by javafx
    @Bean
    public ResourceBundle resourceBundle(){
        return ResourceBundle.getBundle(MESSAGES_LOCATION);
    }
}
