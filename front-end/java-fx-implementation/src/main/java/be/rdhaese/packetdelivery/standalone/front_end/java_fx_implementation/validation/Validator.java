package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation;

import org.springframework.stereotype.Component;

/**
 * Created on 14/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class Validator {

    public static final Integer MAX_LENGHT = 200;

    public Boolean isNotNull(String s) {
        return s != null;
    }

    public Boolean isNotEmpty(String s) {
        return !s.trim().isEmpty();
    }

    public Boolean isNotNullNorEmpty(String s) {
        return isNotNull(s) && isNotEmpty(s);
    }

    public Boolean isNotToLong(String s) {
        if (s == null) {
            return true;
        }
        return s.length() <= MAX_LENGHT;
    }

    public Boolean isValidInput(String s) {
        return isNotNullNorEmpty(s) && isNotToLong(s);
    }

    public Boolean isValidPhoneNumber(String s) {
        /*Matches i.e.:
        * +32 467/645564
        * +1 467/165653
        * 0032 497/164353
        * 0497/145868
        * +32 54/002568
        * 0032 54/458969
        * 054/124568
        * */
        return isNotNullNorEmpty(s) && s.matches("((([+]|[0]{2})([\\d]{1,2})([ ])([\\d]{2,3}))|([\\d]{3,4}))([/])([\\d]{6})");
    }

    public Boolean isValidEmailAddress(String s) {
        if (isNotNullNorEmpty(s)){
            if (!s.contains(" ")){
                return s.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
            }
        }
        return false;
    }

    public Boolean isValidCity(String s) {
        return isValidInput(s);
    }

    public Boolean isValidPostalCode(String s) {
        return isNotNullNorEmpty(s) && s.matches("[0-9]{4}");
    }

    public Boolean isValidStreet(String s) {
        return isValidInput(s);
    }

    public Boolean isValidNumber(String s) {
        return isNotNullNorEmpty(s) && s.matches("[0-9]{1,4}");
    }

    public Boolean isValidMailbox(String s) {
        return isNotToLong(s);
    }

    public Boolean isValidClientName(String s) {
        return isValidInput(s);
    }

    public Boolean isValidUserName(String s) {
        return isValidInput(s);
    }

    public Boolean isValidPassWord(String s) {
        return isValidInput(s);
    }

    public Boolean isValidWebServiceUrl(String s) {
        return isNotNullNorEmpty(s);
    }
}
