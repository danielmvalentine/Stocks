package model;

import java.util.Date;

/**
 * Our public class that we will use to represent a stock. A stock has a ticker symbol and
 * a number of shares.
 */
public class Stock {
  private final String ticker;
  private double shares;

  /**
   * Creates a Stock Object with a ticker symbol and a number of shares.
   *
   * @param ticker The ticker symbol for this stock.
   * @param shares The number of shares for this stock.
   */
  public Stock(String ticker, double shares) {
    if (ticker.length() != 4) {
      throw new IllegalArgumentException("ticker length must be 4");
    }
    this.ticker = ticker.toUpperCase();
    this.shares = shares;
  }

  /**
   * Returns the ticker symbol for this stock. Used for formatting and equivalence checks.
   *
   * @return The ticker symbol of this stock formatted as a string.
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Returns the number of shares for this stock. Used for formatting and equivalence checks.
   * Used for formatting and calculation of total value of portfolios.
   *
   * @return The number of shares of this stock formatted as a double.
   */
  public double getShares() {
    return shares;
  }
}
