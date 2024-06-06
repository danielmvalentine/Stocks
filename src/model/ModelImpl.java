package model;

import java.time.LocalDate;
import java.util.ArrayList;

import model.functions.PortfolioOptions;
import model.functions.ProgramFunction;
import model.functions.StockGainOrLoss;
import model.functions.XDayCrossovers;
import model.functions.XDayMovingAverage;
import model.portfolio.IPortfolio;
import model.portfolio.PortfolioImpl;

public class ModelImpl implements Model {

  ArrayList<IPortfolio> portfolios = new ArrayList<IPortfolio>();


  @Override
  public ProgramFunction gainOrLossOverTime(String stockName,
                                            LocalDate initialDate, LocalDate finalDate) {
    return new StockGainOrLoss(stockName, initialDate, finalDate);
  }

  @Override
  public ProgramFunction movingAverage(String stockName, LocalDate date, int xValue) {
    return new XDayMovingAverage(stockName, date, xValue);
  }

  @Override
  public ProgramFunction xDayCrossovers(String stockName,
                                        LocalDate initialDate, LocalDate finalDate, int xValue) {
    return new XDayCrossovers(stockName, initialDate, finalDate, xValue);
  }

  @Override
  public ProgramFunction portfolioOptions() {
    return new PortfolioOptions(this);
  }

  @Override
  public IPortfolio getPortfolio(String title) {
    for(IPortfolio portfolio : portfolios) {
      if (portfolio.getPortfolioTitle().equals(title)) {
        return portfolio;
      }
    }
    return null;
  }

  @Override
  public ArrayList<IPortfolio> getAllPortfolios() {
    return portfolios;
  }


  @Override
  public String formatPortfolios() {
    if (portfolios.isEmpty()) {
      return "No portfolios found";
    }

    String output = "";

    for (IPortfolio portfolio : portfolios) {
      output.concat("; " + portfolio.getPortfolioTitle());
    }

    return output.substring(2);
  }

  @Override
  public void addPortfolio(IPortfolio portfolio) {
    if (!portfolios.contains(portfolio)) {
      portfolios.add(portfolio);
    }
  }
}
