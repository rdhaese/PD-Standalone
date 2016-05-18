package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.comparator;


import junit.framework.TestCase;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created on 17/05/2016.
 *
 * @author Robin D'Haese
 */
public class DateAsStringComparatorTest extends TestCase{

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Comparator<String> dateAsStringComparator;

    private String date1;
    private String date2;
    private String date3;

    @Before
    public void setUp(){
        dateAsStringComparator = new dateAsStringComparator(dateFormat);

        date1 = "17/04/2016";
        date2 = "20/04/2016";
        date3 = "17/04/2016";
    }

    @Test
    public void testCompare(){
        assertTrue(dateAsStringComparator.compare(date1, date2) < 0);
        assertTrue(dateAsStringComparator.compare(date2, date1) > 0);
        assertTrue(dateAsStringComparator.compare(date1, date3) == 0);
    }
}
