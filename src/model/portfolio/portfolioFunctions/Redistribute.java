package model.portfolio.portfolioFunctions;

import java.time.LocalDate;

import model.Model;
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
    Double totalValue = Double.valueOf(totalValueString);

    Double desiredPrice;
    Double desiredShares;
    Double priceOfStock;

    for (int i = 0; i < redistribution.length; i++) {
      desiredPrice = (redistribution[i] / 100) * totalValue;
      priceOfStock = this.portfolio.getSharePriceOfStock(i);

      desiredShares = desiredPrice / priceOfStock;

      if (desiredShares > this.portfolio.getStockByIndex(i).getShares()) {

      } else if (desiredShares < this.portfolio.getStockByIndex(i).getShares()) {

      }
    }
  }
}
