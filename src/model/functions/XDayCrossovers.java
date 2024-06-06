package model.functions;

import java.time.LocalDate;
import java.util.ArrayList;

import model.AccessApi;

/**
 * Public class for finding the number of x-day crossovers for stock.
 */
public class XDayCrossovers implements ProgramFunction {
  String tag;
  LocalDate dateOne;
  LocalDate dateTwo;
  int x;

  /**
   * Creates a new XDayCrossover Object.
   *
   * @param tag     The name of the Stock.
   * @param dateOne The first date of the Stock.
   * @param dateTwo The second date of the Stock.
   * @param x       The number for the x-day moving average.
   */
  public XDayCrossovers(String tag, LocalDate dateOne, LocalDate dateTwo, int x) {
    this.tag = tag;
    this.dateOne = dateOne;
    this.dateTwo = dateTwo;
    this.x = x;
  }

  /**
   * Our method that we will use to find the x-day crossovers of a given stock in the
   * given period of time.
   *
   * @return Return an int formatted as a String for the average.
   */
  @Override
  public String execute() {
    return "";
  }

  private String helperXDayCross(){
    String bigData = new AccessApi(tag).returnData(dateOne.toString(), dateTwo.toString());
    String[] bigDataSplit = bigData.split(",");
    boolean atFirstDate = false;
    ArrayList<String> listOfCrossDays = new ArrayList<String>();
    for(int i = 0; i < bigDataSplit.length; i++) {
      if (bigDataSplit[i].equals(dateOne.toString())) {
        atFirstDate = true;
      }
      if (atFirstDate && bigDataSplit[i].contains("-")) {
        double dayAverage = (Double.parseDouble(bigDataSplit[i + 1])
                + Double.parseDouble(bigDataSplit[i + 2] + Double.parseDouble(bigDataSplit[i + 3]))
                + Double.parseDouble(bigDataSplit[i + 4])) / 4;
        double dayFinal = Double.parseDouble(bigDataSplit[i + 4]);
        if (dayFinal > dayAverage) {
          listOfCrossDays.add(bigDataSplit[i]);
        }
      }
      if (bigDataSplit[i].equals(dateTwo.toString())) {
        atFirstDate = false;
      }
    }
    StringBuilder finalStringBuilder = new StringBuilder();
    int counterForNewLine = 0;
    for(int i = 0; i < listOfCrossDays.size(); i++) {
      finalStringBuilder.append(listOfCrossDays.get(i));
      finalStringBuilder.append(", ");
      counterForNewLine++;
      if(counterForNewLine > 6){
        finalStringBuilder.append(System.lineSeparator());
        counterForNewLine = 0;
      }
    }
    return finalStringBuilder.toString();
  }
}
