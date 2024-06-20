package model.portfolios;

import java.io.IOException;
import java.time.LocalDate;

import model.Stock;

/**
 * Represents a stock portfolio and holds all the functions relating to a portfolio.
 */
public interface IPortfolio {

  /**
   * Returns the title of the portfolio, and is used for formatting, and equivalency checks.
   *
   * @return The title of the portfolio.
   */
  String getPortfolioTitle();

  /**
   * Finds the stock within this portfolio with the given ticker symbol.
   *
   * @param ticker The ticker symbol for the desired stock.
   * @return The stock with the given ticker symbol.
   */
  Stock getStock(String ticker);

  /**
   * Formats all the stock within this portfolio, including their ticker symbol, and the number of
   * shares the portfolio has.
   *
   * @return The formatted stocks as a String.
   */
  String formatStock();

  /**
   * Formats all the stock within this portfolio, including their ticker symbol, and the number
   * of shares the portfolio has, given the date entered
   * @param date
   * @return
   */
  String formatStockOnDate(LocalDate date);

  /**
   * A method to get the current info in the portfolio.
   *
   * @param givenDate the date given to assess the portfolio on.
   * @return Returns the current data in the portfolio as a structured String.
   */
  String getPortfolioValue(LocalDate givenDate);

  /**
   * Formats the value of each stock in the portfolio.
   *
   * @param givenDate the date given to assess the portfolio on.
   * @return  Returns the current data in the portfolio as a structured String.
   */
  String distributionOfValue(LocalDate givenDate);

  /**
   * A method to add a new stock to our portfolio.
   *
   * @param inputStock The stock we wish to add to our portfolio.
   */
  void addToPortfolio(Stock inputStock);

  /**
   * A method to remove a stock from our portfolio.
   *
   * @param removeStock The stock we wish to remove from our portfolio.
   */
  void removeFromPortfolio(String removeStock);

  /**
   * Saves the given portfolio as a .txt file.
   */
  void savePortfolio() throws IOException;

  /**
   * Gets the values of a portfolio over time as a bar chart.
   *
   * @param dateOne The date to start on.
   * @param dateTwo The date to end on.
   * @return Returns the bar chart as a String.
   */
  String getPortfolioOverTime(LocalDate dateOne, LocalDate dateTwo);

  /**
   * Gets the number of stocks within this portfolio.
   *
   * @return The number of stocks within this portfolio as an int.
   */
  int numberOfStocks();

  /**
   * Finds and returns the most recent share price of a stock given its index in this portfolio.
   * @param stockIndex  The index of the desired stock.
   * @return  The most recent share price of the given stock
   */
  Double getSharePriceOfStock(int stockIndex);


  Stock getStockByIndex(int stockIndex);
}
