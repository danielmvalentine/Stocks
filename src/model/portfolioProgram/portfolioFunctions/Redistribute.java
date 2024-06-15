package model.portfolioProgram.portfolioFunctions;

import java.time.LocalDate;

import model.Model;
import model.Stock;
import model.portfolioProgram.IPortfolio;
import view.PortfolioView;

/**
 * Takes a portfolio and rebalances it given desired percentages by the user.
 */
public class Redistribute implements PortfolioFunction {
  private final Model model;
  private final PortfolioView view;
  private final double[] redistribution;
  private final String title;

  private final IPortfolio portfolio;

  /**
   * Creates a new PortfolioFunction Object, Redistribute.
   *
   * @param model          The model containing the portfolio of interest.
   * @param view           Displays certain aspects of other methods used within this class.
   * @param redistribution The list of the desired distributions.
   * @param title          The title of the portfolio being rebalanced.
   * @throws IllegalArgumentException In the case that the redistribution percentages do not add
   *                                  up to 100%.
   */
  public Redistribute(Model model, PortfolioView view, double[] redistribution, String title)
          throws IllegalArgumentException {
    double totalPercentage = 0;
    for (double percentage : redistribution) {
      totalPercentage += percentage;
    }
    if (Double.compare(totalPercentage, 100) != 0) {
      throw new IllegalArgumentException("percentage must be 100");
    }

    this.model = model;
    this.view = view;
    this.redistribution = redistribution;
    this.title = title;

    this.portfolio = this.model.getPortfolio(title);
  }

  /**
   * Redistributes the given portfolio based on the given redistribution values.
   */
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
