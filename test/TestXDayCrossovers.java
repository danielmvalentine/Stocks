import org.junit.Test;

import java.time.LocalDate;

import model.stockfunctions.XDayCrossovers;

import static org.junit.Assert.assertEquals;

/**
 * Testing public class XDayCrossovers.
 */
public class TestXDayCrossovers {

  /**
   * First test.
   * Testing between two days with no crossover events.
   */
  @Test
  public void testXDayCrossoversOne() {
    LocalDate dateOne = LocalDate.of(2015, 5, 5);
    LocalDate dateTwo = LocalDate.of(2015, 6, 3);
    XDayCrossovers xDayCrossovers = new XDayCrossovers("GOOL", dateOne, dateTwo, 3);
    assertEquals("The days which have crossover occurrences are as follows: "
                    + "2015-05-07, 2015-05-08, 2015-05-14, 2015-05-18, 2015-05-19, "
                    + "2015-05-20, 2015-05-21, \n"
                    + "2015-05-27, 2015-05-28, 2015-06-02, 2015-06-03, "
            , xDayCrossovers.execute());
  }

  /**
   * A bit longer of a test.
   */
  @Test
  public void testXDayCrossoversTwo() {
    LocalDate dateOne = LocalDate.of(2015, 5, 5);
    LocalDate dateTwo = LocalDate.of(2015, 5, 26);
    XDayCrossovers xDayCrossovers = new XDayCrossovers("GOOG", dateOne, dateTwo, 5);
    assertEquals("The days which have crossover occurrences are as follows: 2015-05-07,"
            + " 2015-05-08, 2015-05-14, 2015-05-18, 2015-05-19, 2015-05-20, 2015-05-21, "
            + System.lineSeparator(), xDayCrossovers.execute());
  }
}
