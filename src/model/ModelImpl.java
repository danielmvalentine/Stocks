package model;

import java.time.LocalDate;

import model.functions.PortfolioOptions;
import model.functions.ProgramFunction;
import model.functions.StockGainOrLoss;
import model.functions.XDayCrossovers;
import model.functions.XDayMovingAverage;

public class ModelImpl implements Model {

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
    return new PortfolioOptions();
  }
}
