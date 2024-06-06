package model.portfolio;

import java.util.ArrayList;
import java.util.Date;

import model.Stock;

public class PortfolioImpl implements IPortfolio {
  private final String title;
  private ArrayList<Stock> stocks;

  public PortfolioImpl(String title) {
    this.title = title;
  }

  @Override
  public String getPortfolioTitle() {
    return title;
  }

  @Override
  public String getPortfolioValue(Date givenDate) {
    return "";
  }

  @Override
  public void addToPortfolio(Stock inputStock) {
    this.stocks.add(inputStock);
  }

  @Override
  public void removeFromPortfolio(Stock removeStock) {

  }
}
