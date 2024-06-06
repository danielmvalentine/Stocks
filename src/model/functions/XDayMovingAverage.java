package model.functions;

import java.time.LocalDate;
import model.AccessApi;

/**
 * Public class for finding the x-day moving average of a stock.
 */
public class XDayMovingAverage implements ProgramFunction {
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
    return "The average change over the past " + x + " days is: " + helperXDayMovingAvg();
  }

  private double helperXDayMovingAvg(){
    // Represents our "XDay", or our date when we go back X many days.
    LocalDate XDay = date.minusDays(x);
    // Represents our big data String that has all the data.
    String bigData = new AccessApi(tag).returnData(XDay.toString(), date.toString());
    String[] dividedData = bigData.split(",");
    boolean atXDay = false;
    double sum = 0;
    int averageCounter = 0;
    for(int i = 0; i < dividedData.length; i++){
      if(dividedData[i].equals(XDay.toString())){
        atXDay = true;
      }
      if(atXDay && dividedData[i].contains("-")){
        sum += Double.parseDouble(dividedData[i+1]);
        averageCounter++;
      }
      if(dividedData[i].equals(date.toString())){
        atXDay = false;
        sum += Double.parseDouble(dividedData[i+1]);
        averageCounter++;
      }
    }
    return sum/averageCounter;
  }
}

