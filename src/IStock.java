import java.util.Date;
import java.util.List;

/**
 * This interface represents certain functions to be offered by a program 
 * that work with stocks. 
 */

public interface IStock {

  /**
   * Allows a user to examine the gain or loss of a stock over a specified period
   * of time. 
   * @param stockName the stock being investigated.
   * @param initialDate the initial date to be searched from.
   * @param finalDate the final date to be searched to.
   * @return The gain or loss of the given stock in the given period of time.
   */
  public double gainOrLossOverTime(String stockName, Date initialDate, Date finalDate);

  /**
   * Allows a user to examine the x-day moving average of a stock for a specified 
   * date and a specified value of x. 
   * @param stockName the stock being investigated.
   * @param date the date to start the investigation on. 
   * @param xValue the number of days before the given date to be examined.
   * @return The average of the given stock over the given time period.
   */
  public double movingAverage(String stockName, Date date, int xValue);

  /**
   * Allow a user to determine which days are x-day crossovers for a specified stock 
   * over a specified date range and a specified value of x. 
   * @param stockName the stock being investigated.
   * @param date the date to start the investigation on.
   * @param xValue the number of days before the given date to be examined. 
   * @return A list of dates that were x-day crossovers over the given date range.
   */
  public List<Date> xDayCrossovers(String stockName, Date date, int xValue);
  
}
