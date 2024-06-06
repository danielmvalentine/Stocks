package model;

import java.util.Date;

/**
 * Our public class that we will use to represent that date of a stock.
 */
public class StockImp implements Stock {

  // Our data for each stock, we represent them as having a price, and date.

  private double currentPrice;
  private Date currentDate;

  /**
   * Constructor for Stock.
   * @param currentPrice Our data type that we will use to represent the current price.
   */
  public StockImp(double currentPrice, Date currentDate){
    this.currentPrice = currentPrice;
    this.currentDate = currentDate;
  }


  @Override
  public double getCurrentPrice(){
    return this.currentPrice;
  }

  /**
   * Simple method for getting the current date of the stock.
   * @return Return the current date of the stock.
   */
  @Override
  public Date getCurrentDate(){
    return this.currentDate;
  }

}
