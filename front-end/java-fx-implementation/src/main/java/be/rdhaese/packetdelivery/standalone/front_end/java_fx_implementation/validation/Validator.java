package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation;

import org.springframework.stereotype.Component;

/**
 * Created on 14/04/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class Validator {

    private static final Integer MAX_LENGHT = 200;

    public Boolean isNotNull(String s) {
        return s != null;
    }

    public Boolean isNotEmpty(String s) {
        return !s.trim().isEmpty();
    }

    public Boolean isNotNullNorEmpty(String s) {
        return isNotNull(s) && isNotEmpty(s);
    }

    public Boolean isValidInput(String s) {
        return isNotNullNorEmpty(s) && s.length() <= MAX_LENGHT;
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
        return isNotNullNorEmpty(s) && s.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
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
        return true;
    }

    public Boolean isValidClientName(String s) {
        return isValidInput(s);
    }

    public Boolean isValidUserName(String s) {
        return isValidInput(s);
    }

    public Boolean isValidPassWord(String s){
        return isValidInput(s);
    }

    public Boolean isValidWebServiceUrl(String s){
        //From:
        //https://mathiasbynens.be/demo/url-regex
        return isNotNullNorEmpty(s) && s.matches("_^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!10(?:\\.\\d{1,3}){3})(?!127(?:\\.\\d{1,3}){3})(?!169\\.254(?:\\.\\d{1,3}){2})(?!192\\.168(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\x{00a1}-\\x{ffff}0-9]+-?)*[a-z\\x{00a1}-\\x{ffff}0-9]+)(?:\\.(?:[a-z\\x{00a1}-\\x{ffff}0-9]+-?)*[a-z\\x{00a1}-\\x{ffff}0-9]+)*(?:\\.(?:[a-z\\x{00a1}-\\x{ffff}]{2,})))(?::\\d{2,5})?(?:/[^\\s]*)?$_iuS");
    }
}
