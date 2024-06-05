package model;

import java.util.Date;

/**
 * Our public class that we will use to represent that date of a stock.
 */
public class Stock {

  // Our data for each stock, we represent them as having a price, and date.

  private double currentPrice;
  private Date currentDate;

  /**
   * Constructor for Stock.
   * @param currentPrice Our data type that we will use to represent the current price.
   */
  public Stock(double currentPrice, Date currentDate){
    this.currentPrice = currentPrice;
    this.currentDate = currentDate;
  }

  /**
   * Simple method for getting the current price of the stock.
   * @return Return the current price of the stock.
   */
  public double getCurrentPrice(){
    return this.currentPrice;
  }

  /**
   * Simple method for getting the current date of the stock.
   * @return Return the current date of the stock.
   */
  public Date getCurrentDate(){
    return this.currentDate;
  }

}
