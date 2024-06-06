package model;

import java.util.Date;

/**
 * Public class for finding the gain or loss of a stock.
 */
public class StockGainOrLoss {

  private String stockName;
  private Date dayOne;
  private Date dayTwo;

  /**
   * Constructor for
   * @param stockName
   * @param dayOne
   * @param dayTwo
   */
  public StockGainOrLoss(String stockName, Date dayOne, Date dayTwo) {
    this.stockName = stockName;
    this.dayOne = dayOne;
    this.dayTwo = dayTwo;
  }

  public double mainGainOrLoss(String stock, Date dayOne, Date dayTwo){

  }

}
