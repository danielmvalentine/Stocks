package model;

import java.util.Date;

/**
 * Our public class that we will use to represent a stock.
 */
public class Stock {
  private final String ticker;
  private double shares;

  public Stock(String ticker, double shares) {
    this.ticker = ticker;
    this.shares = shares;
  }
}
