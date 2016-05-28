package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Robin D'Haese
 */
@Configuration
public class FrontEndConfig {

    public static final String CLASSPATH = "classpath:";
    public static final String MESSAGES_LOCATION = "locale/messages";
    public static final String ENCODING = "UTF-8";
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    //used by spring
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource rrbms = new ReloadableResourceBundleMessageSource();
        rrbms.setBasename(String.format("%s%s", CLASSPATH, MESSAGES_LOCATION));
        rrbms.setDefaultEncoding(ENCODING);
        rrbms.setUseCodeAsDefaultMessage(true);
        return rrbms;
    }

    //used by javafx
    @Bean
    public ResourceBundle resourceBundle() {
        Locale.setDefault(Locale.ENGLISH);
        return ResourceBundle.getBundle(MESSAGES_LOCATION);
    }

    @Bean
    public DateFormat dateFormat() {
        return new SimpleDateFormat(DATE_PATTERN);
    }

}
