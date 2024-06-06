package model.functions;

import java.time.LocalDate;

/**
 * Public class for finding the x-day moving average of a stock.
 */
public class XDayMovingAverage implements ProgramFunction {
  String tag;
  LocalDate date;
  int x;

  /**
   * Creates a new XDayMovingAverage Object.
   *
   * @param tag  The name of the Stock.
   * @param date The date to be started from.
   * @param x    The number of days to go back for the analysis.
   */
  public XDayMovingAverage(String tag, LocalDate date, int x) {
    this.tag = tag;
    this.date = date;
    this.x = x;
  }


  /**
   * Our method that we will use to find the x-day moving average of a given stock in the
   * given period of time.
   *
   * @return Return a double formatted as a String for the average.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    // placeholder for now
    // Can just return the string of the gain/loss
    return "";
  }
}

