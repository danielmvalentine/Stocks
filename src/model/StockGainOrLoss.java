package model;

/**
 * Public class for finding the gain or loss of a stock.
 */
public class StockGainOrLoss implements ProgramFunction {
  String tag;
  String dateOne;
  String dateTwo;

  /**
   * Creates a new StockGainOrLoss Object.
   *
   * @param tag     The name of the Stock.
   * @param dateOne The first date of the Stock.
   * @param dateTwo The second date of the Stock.
   */
  public StockGainOrLoss(String tag, String dateOne, String dateTwo) {
    this.tag = tag;
    this.dateOne = dateOne;
    this.dateTwo = dateTwo;
  }

  /**
   * Our method that we will use to find the gain or loss of a given stock in the given period of
   * time.
   *
   * @return Return a double formatted as a String for the total gain or loss.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    // placeholder for now
    // Can just return the string of the gain/loss
    return "";
  }
}
