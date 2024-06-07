package model;

import java.util.Date;

/**
 * Our public class that we will use to represent a stock.
 */
public class Stock {
  private final String ticker;
  private double shares;

  public Stock(String ticker, double shares) {
    if (ticker.length() != 4) {
      throw new IllegalArgumentException("ticker length must be 4");
    }
    this.ticker = ticker.toUpperCase();
    this.shares = shares;
  }

  public String getTicker() {
    return ticker;
  }

  public double getShares() {
    return shares;
  }
}
