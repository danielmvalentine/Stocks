package model;

import java.util.Date;

public interface Stock {
  /**
   * Simple method for getting the current price of the stock.
   *
   * @return Return the current price of the stock.
   */
  double getCurrentPrice();


  Date getCurrentDate();
}
