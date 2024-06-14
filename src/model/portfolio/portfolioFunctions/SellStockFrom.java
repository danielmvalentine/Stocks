package model.portfolio.portfolioFunctions;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.Model;
import model.Stock;
import model.portfolio.IPortfolio;
import view.PortfolioView;

public class SellStockFrom implements PortfolioFunction {
  private final Model model;
  private final PortfolioView view;
  private final String title;
  private final String stock;
  private final double shares;
  private final LocalDate date;




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


  // The method that takes care of selling the stock and adjusting the portfolio if needed.
  private void sellStock() {
    IPortfolio portfolio = this.model.getPortfolio(title);
    Stock stockOfInterest = portfolio.getStock(stock);
    LocalDate buyDate = stockOfInterest.getBuyDate();

    double leftoverShares = 0;

    Stock leftoverStock = null;

    if (stockOfInterest.getShares() > this.shares) {
      leftoverShares = portfolio.getStock(stock).getShares() - this.shares;
      leftoverStock = new Stock(stockOfInterest.getTicker(), leftoverShares,
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
