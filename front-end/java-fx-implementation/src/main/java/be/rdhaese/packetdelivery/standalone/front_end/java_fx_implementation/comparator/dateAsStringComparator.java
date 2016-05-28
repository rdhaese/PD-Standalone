package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.comparator;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Robin D'Haese
 */
public class dateAsStringComparator implements Comparator<String> {

    private DateFormat dateFormat;

    public dateAsStringComparator(DateFormat dateFormat){
        this.dateFormat =dateFormat;
    }

    @Override
    public int compare(String o1, String o2) {
        try {
            Date date1 = dateFormat.parse(o1);
            Date date2 = dateFormat.parse(o2);
            return date1.compareTo(date2);
        } catch (ParseException e) {
            return -10;
        }
    }
}
