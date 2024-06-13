package model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import model.portfolio.IPortfolio;
import model.stockFunctions.PortfolioOptions;
import model.stockFunctions.StockFunction;
import view.StockView;

/**
 * A Model object that aids in the testing of the controller and the bridge between it and
 * the model.
 */
public class MockModelImpl implements Model {
  final StringBuilder log;

  /**
   * Creates a new MockModelImpl object with a log.
   *
   * @param log The log of the methods that the controller asks this mock to do.
   */
  public MockModelImpl(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public StockFunction gainOrLossOverTime(String stockName,
                                          LocalDate initialDate, LocalDate finalDate) {
    log.append("GainOrLoss: stock=" + stockName +
            " initialDate=" + initialDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " finalDate=" + finalDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            System.lineSeparator());
    return null;
  }

  @Override
  public StockFunction movingAverage(String stockName, LocalDate date, int xValue) {
    log.append("movingAvg: stock=" + stockName +
            " date=" + date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " xVal=" + xValue + System.lineSeparator());
    return null;
  }

  @Override
  public StockFunction xDayCrossovers(String stockName,
                                        LocalDate initialDate, LocalDate finalDate, int xValue) {
    log.append("XDayCrossovers: stock=" + stockName +
            " initialDate=" + initialDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " finalDate=" + finalDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " xVal=" + xValue +
            System.lineSeparator());
    return null;
  }

  @Override
  public StockFunction portfolioOptions(Readable rd, StockView view) {
    log.append("PortfolioOptions: readable=" + rd +
            " view=" + view + System.lineSeparator());
    return new PortfolioOptions(this, rd, view);
  }

  @Override
  public IPortfolio getPortfolio(String title) {
    log.append("getPortfolio: title=" + title + System.lineSeparator());
    return null;
  }

  @Override
  public IPortfolio[] getAllPortfolios() {
    log.append("getAllPortfolios: " + System.lineSeparator());
    return null;
  }

  @Override
  public String formatPortfolios() {
    log.append("formatPortfolios: " + System.lineSeparator());
    return "";
  }

  @Override
  public void addPortfolio(IPortfolio portfolio) {
    log.append("addPortfolio: " + portfolio.getPortfolioTitle() + System.lineSeparator());
  }

  @Override
  public void getFromTxt(String title, String filePath) throws IOException {
    log.append("getFromTxt: title=" + title + "filePath=" + filePath + System.lineSeparator());
  }

  @Override
  public int numberOfPortfolios() {
    log.append("numberOfPortfolios: " + System.lineSeparator());
    return 0;
  }

}
