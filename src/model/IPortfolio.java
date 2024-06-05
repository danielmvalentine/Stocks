package model;
import java.util.Date;

/**
 * The public interface that we will implement when we create portfolios.
 */
public interface IPortfolio {

  /**
   * A method to get the current info in the portfolio.
   * @return Returns the current data in the portfolio as a structured String.
   */
  public String getPortfolioValue(Date givenDate);

  /**
   * A method to add a new stock to our portfolio.
   * @param inputStock The stock we wish to add to our portfolio.
   */
  public void addToPortfolio(Stock inputStock);

  /**
   * A method to remove a stock from our portfolio.
   * @param removeStock The stock we wish to remove from our portfolio.
   */
  public void removeFromPortfolio(Stock removeStock);

}
