package model.functions;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import model.AccessApi;

import static java.lang.Double.isNaN;

/**
 * Public class for finding the gain or loss of a stock.
 */
public class StockGainOrLoss implements ProgramFunction {
  String tag;
  LocalDate dateOne;
  LocalDate dateTwo;

  /**
   * Creates a new StockGainOrLoss Object.
   *
   * @param tag     The name of the Stock.
   * @param dateOne The first date of the Stock.
   * @param dateTwo The second date of the Stock.
   */
  public StockGainOrLoss(String tag, LocalDate dateOne, LocalDate dateTwo) {
    this.tag = tag;
    this.dateOne = dateOne;
    this.dateTwo = dateTwo;
  }

  /**
   * Our method that we will use to find the gain or loss of a given stock in the given period of
   * time.
   *
   * @return Return a double formatted as a String for the total gain or loss.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    double helperResult = helperGainOrLoss();
    if (isNaN(helperResult)) {
      return ("You have hit your maximum limit for accessing the API today."
              + " Please try again tomorrow");
    }
    if (helperResult >= 0) {
      return "The total gained over this period of time is: " + helperResult;
    } else {
      return "The total lost over this period of time is: " + helperResult;
    }
  }

  /**
   * Private helperGainOrLoss takes the big data and the dates and converts them into a new
   * ArrayList of Strings that lasts only from the first date to the last date.
   *
   * @return Returns an ArrayList from the beginning of our data to the end.
   * @throws IllegalArgumentException If dates are backwards.
   */
  private double helperGainOrLoss() throws IllegalArgumentException {

    ArrayList<String> finalData = new ArrayList<>();
    if (dateOne.isBefore(dateTwo)) {
      String bigData = new AccessApi(tag).returnData(dateOne.toString(), dateTwo.toString());
      // For loop that separates everything in the main data string by commas and finds dateOne
      // A for loop will be easier to deal with! with a list!
      boolean atFirst = false;
      String[] separatedData = bigData.split(",");
      finalData.add("");
      for (int i = 0; i < separatedData.length; i++) {
        // If we've hit the first date asked for.
        if (separatedData[i].equals(dateOne.toString())) {
          atFirst = true;
        }
        if (atFirst) {
          finalData.add(separatedData[i]);
        }
        if (separatedData[i].equals(dateTwo.toString())) {
          finalData.add(separatedData[i + 1]);
          finalData.add(separatedData[i + 2]);
          finalData.add(separatedData[i + 3]);
          finalData.add(separatedData[i + 4]);
          finalData.add(separatedData[i + 5]);
          atFirst = false;
        }
      }
    } else {
      throw new IllegalArgumentException("Date one must be before date two.");
    }
    System.out.println(finalData);
    return helpFindFromArrayList(finalData);
  }

  public double helpFindFromArrayList(ArrayList<String> input) {

    // First we find how tall our arraylist is by finding how many lineSeparators there are.
    int height = 0;
    for (int i = 0; i < input.size(); i++) {
      if (input.get(i).contains("-")) {
        height++;
      }
    }
    // largeSum is all doubles (money values) added together.
    double largeSum = 0;
    // Specialized flag just used for grabbing the first value.
    boolean isFirst = true;
    double firstValue = 0;
    String valueNext = "";
    for (int i = 0; i < input.size(); i++) {
      // checks for if it has a dash in it. If true we want to take the next input
      // and convert it to a double because our format is date followed by number.
      if (input.get(i).contains("-")) {
        valueNext = input.get(i + 1);
        largeSum += Double.parseDouble(valueNext);
        if (isFirst) {
          firstValue = Double.parseDouble(input.get(i + 1));
        }
        isFirst = false;
      }
    }
    System.out.println(height);
    double doubleFinalDouble = largeSum / height;
    DecimalFormat decfor = new DecimalFormat("0.000");
    return (Double.parseDouble(decfor.format(doubleFinalDouble - firstValue)));
  }
}
