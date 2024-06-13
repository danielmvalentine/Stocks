package model.portfolio;

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
   * Formats all the stock within this portfolio, including their ticker symbol, the number of
   * shares the portfolio has, and the date it was purchased on.
   *
   * @return The formatted stocks as a String.
   */
  String formatStock();

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


}
