package model.portfolio.portfolioFunctions;

import java.time.LocalDate;

import model.Model;
import model.Stock;
import view.PortfolioView;

public class AddStockTo implements PortfolioFunction {
  private final Model model;
  private final PortfolioView view;
  private final String title;
  private final String stock;
  private final double shares;
  private final LocalDate date;

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
