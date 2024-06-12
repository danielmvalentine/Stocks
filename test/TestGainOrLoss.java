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
      StockGainOrLoss testStock = new StockGainOrLoss("GOOL", testDateTwo, testDateOne);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Date one must be before date two.");
    }
  }

  /**
   * Testing over a shorter frame to see that it should decrease.
   */
  @Test
  public void testLossTime() {
    LocalDate testDateOne = LocalDate.of(2015, 5, 5);
    LocalDate testDateTwo = LocalDate.of(2015, 6, 3);
    StockGainOrLoss testStock = new StockGainOrLoss("GOOL", testDateOne, testDateTwo);
    assertEquals("The total lost over this period of time is: -2.873",
            testStock.execute());
  }

  /**
   * Testing over a long period of time that it will gain.
   */
  @Test
  public void testGainTime(){
    LocalDate testDateOne = LocalDate.of(2015, 5, 5);
    LocalDate testDateTwo = LocalDate.of(2017, 7, 3);
    StockGainOrLoss testStock = new StockGainOrLoss("GOOL", testDateOne, testDateTwo);
    assertEquals("The total gained over this period of time is: 200.688",
            testStock.execute());
  }
}
