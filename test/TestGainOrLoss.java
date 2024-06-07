import org.junit.Test;

import java.time.LocalDate;

import model.functions.StockGainOrLoss;

import static org.junit.Assert.assertEquals;


/**
 * Testing StockGainOrLoss.
 */
public class TestGainOrLoss {

  /**
   * Testing that it should fail if you put the dates in out of order.
   */
  @Test
  public void testBackwardsFailDates() {
    try {
      LocalDate testDateOne = LocalDate.of(2015, 5, 5);
      LocalDate testDateTwo = LocalDate.of(2015, 6, 3);
      StockGainOrLoss testStock = new StockGainOrLoss("GOOG", testDateTwo, testDateOne);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Date one must be before date two.");
    }
  }

  /**
   * Another simple test but this time over a much longer frame.
   */
  @Test
  public void testLongTime() {
    LocalDate testDateOne = LocalDate.of(2015, 5, 5);
    LocalDate testDateTwo = LocalDate.of(2015, 6, 3);
    StockGainOrLoss testStock = new StockGainOrLoss("GOOG", testDateOne, testDateTwo);
    assertEquals("The total lost over this period of time is: -2.873",
            testStock.execute());
  }
}
