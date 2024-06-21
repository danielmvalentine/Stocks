package model.portfolios.portfoliofunctions;

import java.time.LocalDate;

import model.Model;
import model.Stock;
import model.portfolios.IPortfolio;
import view.PortfolioView;

/**
 * Will sell an amount of shares from a stock in a given portfolio.
 */
public class SellStockFrom implements PortfolioFunction {
  private final Model model;
  private final PortfolioView view;
  private final String title;
  private final String stock;
  private final double shares;
  private final LocalDate date;


  /**
   * Creates a new PortfolioFunction Object, SellStockFrom.
   *
   * @param model The model containing the portfolio of interest.
   * @param view  Displays any issues that are found within the execution of the program.
   * @param title The title of the portfolio of interest.
   * @param stock The stock to be sold.
   * @param shares  The number of shares to be sold (all shares will be sold if this value
   *                    is greater than the shares present).
   * @param date  The date that the stocks should be sold on.
   */
  public SellStockFrom(Model model, PortfolioView view, String title,
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
   * Will sell a given amount of shares of a given stock.
   */
  @Override
  public void execute() {
    // Checks that the given portfolio exists
    if (this.model.getPortfolio(title) != null) {
      // Checks that the given stock exists
      if (this.model.getPortfolio(title).getStock(stock) != null) {
        // Tells the model to sell a stock from a portfolio
        sellStock();
      } else {
        // If the stock doesn't exist in the portfolio, informs the user and resets.
        view.writeMessage("Stock does not exist." + System.lineSeparator());
      }
    }
    else {
      // If the portfolio doesn't exist, informs the user and resets.
      view.writeMessage("Portfolio does not exist." + System.lineSeparator());
    }
  }


  // The method that takes care of selling the stock and adjusting the current shares if needed.
  private void sellStock() {
    IPortfolio portfolio = this.model.getPortfolio(title);
    Stock stockOfInterest = portfolio.getStock(stock);

    double leftoverShares = 0;

    Stock leftoverStock = null;

    if (stockOfInterest.getShares() > this.shares) {
      leftoverShares = portfolio.getStock(stock).getShares() - this.shares;
      leftoverStock = new Stock(stockOfInterest.getTicker(),
              leftoverShares,
              stockOfInterest.getBuyDate());
    }


    Stock originalStock = new Stock(stockOfInterest.getTicker(),
            stockOfInterest.getShares() - leftoverShares,
            stockOfInterest.getBuyDate());
    originalStock.addSellDate(date);


    portfolio.removeFromPortfolio(stock);
    portfolio.addToPortfolio(originalStock);

    if (leftoverStock != null) {
      portfolio.addToPortfolio(leftoverStock);
    }

    view.writeMessage("Stock successfully sold from the portfolio."
            + System.lineSeparator());
  }

}
