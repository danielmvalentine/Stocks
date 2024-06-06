package model;

/**
 * Public class for finding the number of x-day crossovers for stock.
 */
public class XDayCrossovers implements ProgramFunction {
  String tag;
  String dateOne;
  String dateTwo;
  int x;

  /**
   * Creates a new XDayCrossover Object.
   *
   * @param tag     The name of the Stock.
   * @param dateOne The first date of the Stock.
   * @param dateTwo The second date of the Stock.
   * @param x       The number for the x-day moving average.
   */
  public XDayCrossovers(String tag, String dateOne, String dateTwo, int x) {
    this.tag = tag;
    this.dateOne = dateOne;
    this.dateTwo = dateTwo;
    this.x = x;
  }

  /**
   * Our method that we will use to find the x-day crossovers of a given stock in the
   * given period of time.
   *
   * @return Return an int formatted as a String for the average.
   */
  @Override
  public String execute() {
    return "";
  }

}
