package model.portfolios.portfoliofunctions;

import java.time.LocalDate;

import model.Model;
import model.Stock;
import view.PortfolioView;

/**
 * Adds a certain number of shares for a stock to the given portfolio.
 */
public class AddStockTo implements PortfolioFunction {
  private final Model model;
  private final PortfolioView view;
  private final String title;
  private final String stock;
  private final double shares;
  private final LocalDate date;

  /**
   * Creates a new PortfolioFunction Object, AddStockTo.
   *
   * @param model  The model containing the portfolios.
   * @param view   Displays if this function was successful or not.
   * @param title  The name of the portfolio the stock should be added to as a String.
   * @param stock  The name of the stock to be added as a Strong.
   * @param shares The number of shares to be added to the portfolio.
   * @param date   The date the shares were added (bought).
   */
  public AddStockTo(Model model, PortfolioView view, String title,
                    String stock, double shares, LocalDate date) {
    if (stock.length() != 4) {
      throw new IllegalArgumentException();
    }

    this.model = model;
    this.view = view;
    this.title = title;
    this.stock = stock.toUpperCase();
    this.shares = shares;
    this.date = date;
  }

  /**
   * Adds stock to the given portfolio or informs the user of a potential error
   * regarding inputs.
   */
  @Override
  public void execute() {
    if (shares <= 0) {
      view.writeMessage("Can't have negative shares" + System.lineSeparator());
    } else if (this.model.getPortfolio(title) != null) {
      // Tells the model to add some shares of a stock to a portfolio
      this.model.getPortfolio(title).addToPortfolio(new Stock(stock, shares, date));
      view.writeMessage("Stock successfully added to the portfolio" + System.lineSeparator());
    } else {
      // If the portfolio doesn't exist, informs the user and resets.
      view.writeMessage("Portfolio does not exist." + System.lineSeparator());
    }
  }
}
