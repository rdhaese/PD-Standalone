package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation;

import junit.framework.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Created on 17/05/2016.
 *
 * @author Robin D'Haese
 */
public class ValidatorTest extends TestCase {

    private Validator validator;

    @Before
    public void setUp() {
        validator = new Validator();
    }

    @Test
    public void testMaxLenght() {
        assertEquals(200, Validator.MAX_LENGHT.intValue());
    }

    @Test
    public void testIsNotNull() {
        assertTrue(validator.isNotNull(""));
        assertFalse(validator.isNotNull(null));
    }

    @Test
    public void testIsNotEmpty() {
        assertTrue(validator.isNotEmpty("a"));
        assertFalse(validator.isNotEmpty(""));
    }

    @Test
    public void testIsNotNullOrEmpty() {
        assertTrue(validator.isNotNullNorEmpty("a"));
        assertFalse(validator.isNotNullNorEmpty(null));
        assertFalse(validator.isNotNullNorEmpty(""));
    }

    @Test
    public void testIsNotToLong() {
        assertTrue(validator.isNotToLong(null));
        assertTrue(validator.isNotToLong(""));
        assertTrue(validator.isNotToLong(RandomStringUtils.random(200)));
        assertFalse(validator.isNotToLong(RandomStringUtils.random(201)));
    }

    @Test
    public void testIsValidInput() {
        assertTrue(validator.isValidInput("a"));
        assertTrue(validator.isValidInput(RandomStringUtils.random(Validator.MAX_LENGHT)));
        assertFalse(validator.isValidInput(RandomStringUtils.random(Validator.MAX_LENGHT + 1)));
        assertFalse(validator.isValidInput(null));
        assertFalse(validator.isValidInput(""));
    }

    @Test
    public void testIsValidPhoneNumberValids() {
        String[] validPhoneNumbers = new String [] {
                "+32 467/645564",
                "+1 467/165653",
                "0032 000/165897",
                "0497/146898",
                "+32 54/002548",
                "0032 54/002458",
                "054/457878"
        };

        for (String validPhoneNumber : validPhoneNumbers){
            assertTrue(validPhoneNumber, validator.isValidPhoneNumber(validPhoneNumber));
        }
    }

    @Test
    public void testIsValidPhoneNumberInValids() {
        String[] invalidPhoneNumbers = new String [] {
                "+32 467645564",
                "+32467/645564",
                "1 467/165653",
                "0032 0/165897",
                "0497/14689",
                "+ 54/002548",
                "0032 /002458",
                "054/457878475578"
        };

        for (String invalidPhoneNumber : invalidPhoneNumbers){
            assertFalse(invalidPhoneNumber, validator.isValidPhoneNumber(invalidPhoneNumber));
        }
    }

    @Test
    public void testIsValidEmailAddressValids() {
        String[] validEmails = new String[]{
                "email@example.com",
                "firstname.lastname@example.com",
                "email@subdomain.example.com",
                "firstname+lastname@example.com",
                "email@123.123.123.123",
                "email@[123.123.123.123]",
                "1234567890@example.com",
                "email@example-one.com",
                "_______@example.com",
                "email@example.name",
                "email@example.museum",
                "email@example.co.jp",
                "firstname-lastname@example.com"
        };

        for (String validEmail : validEmails){
            assertTrue(validEmail, validator.isValidEmailAddress(validEmail));
        }
    }

    @Test
    public void testIsValidEmailAddressInvalids(){
        String[] invalidEmails = new String[]{
                "plainaddress",
                "@example.com",
                "Joe Smith <email@example.com>",
                "email.example.com",
                "email@example@example.com",
                ".email@example.com",
                "email.@example.com",
                "email..email@example.com",
                "あいうえお@example.com",
                "email@example.com (Joe Smith)",
                "email@example",
                "email@-example.com",
                "email@example..com",
                "Abc..123@example.com"
        };

        for (String invalidEmail : invalidEmails){
            assertFalse(invalidEmail, validator.isValidEmailAddress(invalidEmail));
        }
    }

    @Test
    public void testIsValidCity() {
        assertTrue(validator.isValidCity("a"));
        assertTrue(validator.isValidCity(RandomStringUtils.random(Validator.MAX_LENGHT)));
        assertFalse(validator.isValidCity(RandomStringUtils.random(Validator.MAX_LENGHT + 1)));
        assertFalse(validator.isValidCity(null));
        assertFalse(validator.isValidCity(""));
    }

    @Test
    public void testIsValidPostalCode() {
        assertTrue(validator.isValidPostalCode(RandomStringUtils.randomNumeric(4)));
        assertFalse(validator.isValidPostalCode(RandomStringUtils.randomNumeric(3)));
        assertFalse(validator.isValidPostalCode(RandomStringUtils.randomNumeric(5)));
        assertFalse(validator.isValidPostalCode("abcd"));
        assertFalse(validator.isValidPostalCode(null));
        assertFalse(validator.isValidPostalCode(""));
    }

    @Test
    public void testIsValidStreet() {
        assertTrue(validator.isValidStreet("a"));
        assertTrue(validator.isValidStreet(RandomStringUtils.random(Validator.MAX_LENGHT)));
        assertFalse(validator.isValidStreet(RandomStringUtils.random(Validator.MAX_LENGHT + 1)));
        assertFalse(validator.isValidStreet(null));
        assertFalse(validator.isValidStreet(""));
    }

    @Test
    public void testIsValidNumber() {
        assertTrue(validator.isValidNumber(RandomStringUtils.randomNumeric(1)));
        assertTrue(validator.isValidNumber(RandomStringUtils.randomNumeric(2)));
        assertTrue(validator.isValidNumber(RandomStringUtils.randomNumeric(3)));
        assertTrue(validator.isValidNumber(RandomStringUtils.randomNumeric(4)));
        assertFalse(validator.isValidNumber(RandomStringUtils.randomNumeric(5)));
        assertFalse(validator.isValidNumber("a"));
        assertFalse(validator.isValidNumber("abcd"));
        assertFalse(validator.isValidNumber(null));
        assertFalse(validator.isValidNumber(""));
    }

    @Test
    public void testIsValidMailbox() {
        assertTrue(validator.isValidMailbox(null));
        assertTrue(validator.isValidMailbox(""));
        assertTrue(validator.isValidMailbox(RandomStringUtils.random(200)));
        assertFalse(validator.isValidMailbox(RandomStringUtils.random(201)));
    }

    @Test
    public void testIsValidClientName() {
        assertTrue(validator.isValidClientName("a"));
        assertTrue(validator.isValidClientName(RandomStringUtils.random(Validator.MAX_LENGHT)));
        assertFalse(validator.isValidClientName(RandomStringUtils.random(Validator.MAX_LENGHT + 1)));
        assertFalse(validator.isValidClientName(null));
        assertFalse(validator.isValidClientName(""));
    }

    @Test
    public void testIsValidUserName() {
        assertTrue(validator.isValidUserName("a"));
        assertTrue(validator.isValidUserName(RandomStringUtils.random(Validator.MAX_LENGHT)));
        assertFalse(validator.isValidUserName(RandomStringUtils.random(Validator.MAX_LENGHT + 1)));
        assertFalse(validator.isValidUserName(null));
        assertFalse(validator.isValidUserName(""));
    }

    @Test
    public void testIsValidPassword() {
        assertTrue(validator.isValidPassWord("a"));
        assertTrue(validator.isValidPassWord(RandomStringUtils.random(Validator.MAX_LENGHT)));
        assertFalse(validator.isValidPassWord(RandomStringUtils.random(Validator.MAX_LENGHT + 1)));
        assertFalse(validator.isValidPassWord(null));
        assertFalse(validator.isValidPassWord(""));
    }

    @Test
    public void testIsValidWebServiceUrl() {
        assertTrue(validator.isValidWebServiceUrl("a"));
        assertFalse(validator.isValidWebServiceUrl(null));
        assertFalse(validator.isValidWebServiceUrl(""));
    }
}
