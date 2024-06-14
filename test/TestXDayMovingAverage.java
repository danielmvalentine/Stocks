import org.junit.Test;

import java.time.LocalDate;

import model.stockFunctions.XDayMovingAverage;

import static org.junit.Assert.assertEquals;

/**
 * Testing the XDayMovingAverage class.
 */
public class TestXDayMovingAverage {

  /**
   * First test.
   */
  @Test
  public void testXDayMovingAverageOne() {
    LocalDate dateOne = LocalDate.of(2015, 5, 5);
    XDayMovingAverage xDayMovingAverage = new XDayMovingAverage("GOOL", dateOne, 5);
    assertEquals("The average change over the past 5 days is: +$540.25",
            xDayMovingAverage.execute());
  }

  /**
   * Second test will be much longer.
   */
  @Test
  public void testXDayMovingAverageLong() {
    LocalDate dateOne = LocalDate.of(2017, 5, 5);
    XDayMovingAverage xDayMovingAverage = new XDayMovingAverage("GOOL", dateOne, 50);
    assertEquals("The average change over the past 50 days is: +$630.362",
            xDayMovingAverage.execute());
  }
}
