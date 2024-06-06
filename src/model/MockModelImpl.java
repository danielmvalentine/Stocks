package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import model.functions.ProgramFunction;

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
    log.append("GainOrLoss: stock=" + stockName +
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
}
