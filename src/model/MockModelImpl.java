package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import model.functions.ProgramFunction;
import model.portfolio.IPortfolio;

public class MockModelImpl implements Model {
  final StringBuilder log;

  /**
   * Creates a new MockModelImpl object with a log.
   * @param log   The log of the methods that the controller asks this mock to do.
   */
  public MockModelImpl(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public ProgramFunction gainOrLossOverTime(String stockName,
                                            LocalDate initialDate, LocalDate finalDate) {
    log.append("GainOrLoss: stock=" + stockName +
            " initialDate=" + initialDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " finalDate=" + finalDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            System.lineSeparator());
    return null;
  }

  @Override
  public ProgramFunction movingAverage(String stockName, LocalDate date, int xValue) {
    log.append("movingAvg: stock=" + stockName +
            " date=" + date.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " xVal=" + xValue + System.lineSeparator());
    return null;
  }

  @Override
  public ProgramFunction xDayCrossovers(String stockName,
                                        LocalDate initialDate, LocalDate finalDate, int xValue) {
    log.append("XDayCrossovers: stock=" + stockName +
            " initialDate=" + initialDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " finalDate=" + finalDate.format(DateTimeFormatter.ISO_LOCAL_DATE) +
            " xVal=" + xValue +
            System.lineSeparator());
    return null;
  }

  @Override
  public ProgramFunction portfolioOptions() {
    log.append("PortfolioOptions: \n");
    return null;
  }

  @Override
  public IPortfolio getPortfolio(String title) {
    log.append("getPortfolio: title=" + title + System.lineSeparator());
    return null;
  }

  @Override
  public ArrayList<IPortfolio> getAllPortfolios() {
    log.append("getAllPortfolios: \n");
    return null;
  }

  @Override
  public String formatPortfolios() {
    log.append("formatPortfolios: \n");
    return "";
  }

  @Override
  public void addPortfolio(IPortfolio portfolio) {
    log.append("addPortfolio: \n" + portfolio.toString());
  }
}
