package model.stockfunctions;

import java.text.DecimalFormat;
import java.time.LocalDate;

import model.AccessApi;

import static java.lang.Double.isNaN;

/**
 * Public class for finding the x-day moving average of a stock.
 */
public class XDayMovingAverage implements StockFunction {
  String tag;
  LocalDate date;
  int x;

  /**
   * Creates a new XDayMovingAverage Object.
   *
   * @param tag  The name of the Stock.
   * @param date The date to be started from.
   * @param x    The number of days to go back for the analysis.
   */
  public XDayMovingAverage(String tag, LocalDate date, int x) {
    this.tag = tag;
    this.date = date;
    this.x = x;
  }


  /**
   * Our method that we will use to find the x-day moving average of a given stock in the
   * given period of time.
   *
   * @return Return a double formatted as a String for the average.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    if (isNaN(helperXDayMovingAvg())) {
      return ("You have hit your maximum limit for accessing the API today."
              + " Please try again tomorrow");
    }
    if (helperXDayMovingAvg() >= 0) {
      return "The average change over the past " + x + " days is: +$" + helperXDayMovingAvg();
    } else {
      return "The average change over the past " + x + " days is: -$" + helperXDayMovingAvg();
    }
  }

  private double helperXDayMovingAvg() {
    // Represents our "XDay", or our date when we go back X many days.
    LocalDate xDay = date.minusDays(x);
    // Represents our big data String that has all the data.
    String bigData = new AccessApi(tag).returnData(xDay.toString(), date.toString());
    bigData = bigData.replaceAll(System.lineSeparator(), ",");
    bigData = bigData.replaceAll("\\s", "");
    String[] dividedData = bigData.split(",");
    boolean atXDay = false;
    double sum = 0;
    int averageCounter = 0;
    for (int i = 0; i < dividedData.length; i++) {
      if (dividedData[i].equals(xDay.toString())) {
        atXDay = true;
      }
      if (atXDay && dividedData[i].contains("-")) {
        sum += Double.parseDouble(dividedData[i + 1].replaceAll("\\s", ""));
        averageCounter++;
      }
      if (dividedData[i].equals(date.toString())) {
        atXDay = false;
        sum += Double.parseDouble(dividedData[i + 1].replaceAll("\\s", ""));
        averageCounter++;
      }
    }
    DecimalFormat decfor = new DecimalFormat("0.000");
    return Double.parseDouble(decfor.format(sum / x));
  }
}

