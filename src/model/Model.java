package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


import model.functions.ProgramFunction;
import model.portfolio.IPortfolio;
import view.StockView;

/**
 * This interface represents certain functions to be offered by a program
 * that work with stocks.
 */
public interface Model {

  /**
   * Allows a user to examine the gain or loss of a stock over a specified period
   * of time.
   *
   * @param stockName   the stock being investigated.
   * @param initialDate the initial date to be searched from.
   * @param finalDate   the final date to be searched to.
   * @return The function that will perform this operation.
   */
  ProgramFunction gainOrLossOverTime(String stockName,
                                     LocalDate initialDate, LocalDate finalDate);

  /**
   * Allows a user to examine the x-day moving average of a stock for a specified
   * date and a specified value of x.
   *
   * @param stockName the stock being investigated.
   * @param date      the date to start the investigation on.
   * @param xValue    the number of days before the given date to be examined.
   * @return The function that will perform this operation.
   */
  ProgramFunction movingAverage(String stockName, LocalDate date, int xValue);

  /**
   * Allow a user to determine which days are x-day crossovers for a specified stock
   * over a specified date range and a specified value of x.
   *
   * @param stockName   the stock being investigated.
   * @param initialDate the date to start the investigation on.
   * @param finalDate   the date to end the investigation on.
   * @param xValue      the number of days before the given date to be examined.
   * @return The function that will perform this operation.
   */
  ProgramFunction xDayCrossovers(String stockName,
                                 LocalDate initialDate, LocalDate finalDate, int xValue);

  /**
   * Allow a user to access different functions with portfolios.
   *
   * @return The function that will perform this operation.
   */
  ProgramFunction portfolioOptions(Readable rd, StockView view);


  /**
   * Gets a specific portfolio given its title.
   *
   * @param title The title of the portfolio to be found
   * @return The portfolio with the given title.
   */
  IPortfolio getPortfolio(String title);

  /**
   * Used in the portfolio controller to get the portfolios of the user.
   *
   * @return A List of Portfolios that the user has.
   */
  ArrayList<IPortfolio> getAllPortfolios();

  /**
   * Formats the portfolios as a string.
   *
   * @return The String of the returned portfolios
   */
  String formatPortfolios();

  /**
   * Adds a portfolio to the users list of portfolios.
   *
   * @param portfolio The portfolio to be added
   */
  void addPortfolio(IPortfolio portfolio);

  /**
   * Takes our portfolio and finds its value over a certain set of time.
   * We will represent this as a bar chart of asterisks.
   *
   * @param title The title of the portfolio to be added.
   * @param filePath The path to the .txt file to be read.
   */
  void getFromTxt(String title, String filePath) throws IOException;
}
