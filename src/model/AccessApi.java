package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Public class AccessApi is our class that we use to get the data from the
 * Alpha Vantage API and convert it into a much easier to use CSV file.
 */
public class AccessApi {

  String ticker;

  /**
   * Our constructor.
   *
   * @param ticker The name of the Stock we take in.
   */
  public AccessApi(String ticker) {
    this.ticker = ticker;
  }

  /**
   * Our method that actually returns the data from the API.
   *
   * @param dateOne The first date given.
   * @param dateTwo The second date given.
   * @return Returns the data from the API as a String.
   */
  public String returnData(String dateOne, String dateTwo) {
    String apiKey = "QEFVQOMF9QYHO5D8";
    URL url = null;

    try {
      /*
      create the URL. This is the query to the web service. The query string
      includes the type of query (DAILY stock prices), stock symbol to be
      looked up, the API key and the format of the returned
      data (comma-separated values:csv). This service also supports JSON
      which you are welcome to use.
       */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + "GOOG" + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This returns an InputStream object.
      In the csv format, it returns several lines, each line being separated
      by commas. Each line contains the date, price at opening time, highest
      price for that date, lowest price for that date, price at closing time
      and the volume of trade (no. of shares bought/sold) on that date.

      This is printed below.
       */
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + ticker);
    }

    String tempString = output.toString().replaceAll(System.lineSeparator(), ",");
    String tempStringTwo = tempString.replaceAll("\\s", "");
    String[] tempList = tempStringTwo.split(",");
    ArrayList<String> trueStringList = new ArrayList<String>();
    for (int i = tempList.length - 1; i > 0; i--) {
      if (tempList[i].contains("-")) {
        trueStringList.add(tempList[i]);
        trueStringList.add(tempList[i + 1]);
        trueStringList.add(tempList[i + 2]);
        trueStringList.add(tempList[i + 3]);
        trueStringList.add(tempList[i + 4]);
        trueStringList.add(tempList[i + 5]);
      }
    }
    StringBuilder finalString = new StringBuilder();
    for (int i = 0; i < trueStringList.size(); i++) {
      finalString.append(trueStringList.get(i));
      if (!trueStringList.get(i).equals(System.lineSeparator())) {
        finalString.append(",");
      }
    }
    return finalString.toString();
  }
}
