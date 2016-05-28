package be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation;

import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.comparator.ComparatorTestSuite;
import be.rdhaese.packetdelivery.standalone.front_end.java_fx_implementation.validation.ValidationTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComparatorTestSuite.class,
        ValidationTestSuite.class
})
public class JavaFxImplementationTestSuite {
}
