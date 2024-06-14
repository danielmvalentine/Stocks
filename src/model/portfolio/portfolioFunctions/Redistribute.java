package model.portfolio.portfolioFunctions;

import java.time.LocalDate;

import model.Model;
import model.Stock;
import model.portfolio.IPortfolio;
import view.PortfolioView;

public class Redistribute implements PortfolioFunction {
  private final Model model;
  private final PortfolioView view;
  private final double[] redistribution;
  private final String title;

  private final IPortfolio portfolio;

  public Redistribute(Model model, PortfolioView view, double[] redistribution, String title) {
    this.model = model;
    this.view = view;
    this.redistribution = redistribution;
    this.title = title;

    this.portfolio = this.model.getPortfolio(title);
  }

  @Override
  public void execute() {
    String totalValueString = this.portfolio.getPortfolioValue(LocalDate.now());
    double totalValue = Double.parseDouble(totalValueString);

    double desiredPrice;
    double desiredShares;
    double priceOfStock;

    Stock currentStock;

    for (int i = 0; i < redistribution.length; i++) {
      desiredPrice = (redistribution[i] / 100) * totalValue;
      priceOfStock = this.portfolio.getSharePriceOfStock(i);

      desiredShares = desiredPrice / priceOfStock;

      currentStock = this.portfolio.getStockByIndex(i);

      if (desiredShares > currentStock.getShares()) {
        new AddStockTo(model, view, title,
                currentStock.getTicker(), desiredShares - currentStock.getShares(),
                LocalDate.now()).execute();

      } else if (desiredShares < currentStock.getShares()) {
        new SellStockFrom(model, view, title,
                currentStock.getTicker(), desiredShares, LocalDate.now()).execute();
      }
    }
  }
}
