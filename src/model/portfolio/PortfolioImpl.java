package model.portfolio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.AccessApi;
import model.Stock;

/**
 * The implementation of the IPortfolio interface. Represents a portfolio with a title and
 * a list of stocks in this portfolio.
 */
public class PortfolioImpl implements IPortfolio {
  private final String title;
  private Stock[] stocks;

  /**
   * Creates a new PortfolioImpl object with a title.
   *
   * @param title The title of this portfolio.
   */
  public PortfolioImpl(String title) {
    this.title = title;
    this.stocks = new Stock[0];
  }

  @Override
  public Stock getStock(String ticker) {
    if (stocks == null || stocks.length == 0) {
      return null;
    }
    for (Stock stock : stocks) {
      if (stock.getTicker().equals(ticker)) {
        return stock;
      }
    }
    return null;
  }

  @Override
  public String formatStock() {
    if (stocks == null || stocks.length == 0) {
      return "No stocks found";
    }

    StringBuilder output = new StringBuilder();

    for (int i = 0; i < stocks.length; i += 1) {
      if (stocks[i].getSellDate() == null) {
        output.append(System.lineSeparator()).append("  ");
        output.append(stocks[i].getTicker());
        output.append("; ").append(getTotalShares(stocks[i])).append(" shares");
        i += stockRepeats(stocks[i]) - 1;
      }

    }

    return output.toString();
  }

  private String getTotalShares(Stock stock) {
    double totalShares = 0.0;
    for (Stock repeat : stocks) {
      if (repeat.getTicker().equals(stock.getTicker()) && repeat.getSellDate() == null) {
        totalShares += repeat.getShares();
      }
    }
    return Double.toString(totalShares);
  }

  private int stockRepeats(Stock stock) {
    int numRepeats = 0;
    for (Stock repeat : stocks) {
      if (repeat.getTicker().equals(stock.getTicker()) && repeat.getSellDate() == null) {
        numRepeats += 1;
      }
    }
    return numRepeats;
  }

  @Override
  public String getPortfolioTitle() {
    return title;
  }


  @Override
  public String getPortfolioValue(LocalDate givenDate) {
    // Checks that the date entered is not the future.
    if (!givenDate.isAfter(LocalDate.now())) {

      String bigData = "";
      String[] separatedData;
      double value = 0;
      String date = givenDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

      for (Stock stock : stocks) {
        if (stock.getBuyDate().isBefore(givenDate) &&
                (stock.getSellDate() == null || stock.getSellDate().isAfter(givenDate))) {
          // Gets the data for the given stock
          bigData = new AccessApi(stock.getTicker())
                  .returnData(givenDate.toString(), givenDate.toString());
          separatedData = bigData.split(",");
          // Checking if there is data on the year and month
          if (!bigData.contains(date) && bigData.contains(date.substring(0, 7))) {
            int mostRecentDateIndex = 0;
            for (int i = 0; i < separatedData.length; i += 6) {
              if (separatedData[i].compareTo(date) > 0) {
                break;
              }
              mostRecentDateIndex = i;
            }

            date = separatedData[mostRecentDateIndex];

          } else if (!bigData.contains(date) && !bigData.contains(date.substring(0, 7))){
            return "Date not found";
          }

          // Locates the given date and adds the end of day value per stock times its shares.
          for (int i = 0; i < separatedData.length; i += 6) {
            if (separatedData[i].contains(date)) {
              value += (stock.getShares() * Double.parseDouble(separatedData[i + 4]));
              break;
            }
          }
        }
      }

      return String.valueOf(value);
    }

    return "Date is in the future, please enter a date in the past.";
  }

  @Override
  public String distributionOfValue(LocalDate givenDate) {
    String bigData = "";
    String[] separatedData;
    String date = givenDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    StringBuilder output = new StringBuilder();

    for (Stock stock : stocks) {
      if (stock.getBuyDate().isBefore(givenDate) &&
              (stock.getSellDate() == null || stock.getSellDate().isAfter(givenDate))) {
        // Gets the data for the given stock
        bigData = new AccessApi(stock.getTicker())
                .returnData(givenDate.toString(), givenDate.toString());
        separatedData = bigData.split(",");

        if (!bigData.contains(date) && bigData.contains(date.substring(0, 7))) {
          int mostRecentDateIndex = 0;
          for (int i = 0; i < separatedData.length; i += 6) {
            if (separatedData[i].compareTo(date) > 0) {
              break;
            }
            mostRecentDateIndex = i;
          }

          date = separatedData[mostRecentDateIndex];
        }

        if (bigData.contains(date)) {
          // Locates the given date and adds the end of day value per stock times its shares.
          for (int i = 0; i < separatedData.length; i += 6) {
            if (separatedData[i].contains(date)) {
              output.append(System.lineSeparator());
              output.append(stock.getTicker()).append(": $");
              output.append(stock.getShares() * Double.parseDouble(separatedData[i + 4]));
              break;
            }
          }
        }
      }
    }

    return output.toString();

  }

  @Override
  public void addToPortfolio(Stock inputStock) {
    Stock[] newStocks = new Stock[stocks.length + 1];
    System.arraycopy(stocks, 0, newStocks, 0, stocks.length);
    newStocks[newStocks.length - 1] = inputStock;
    this.stocks = newStocks;
  }

  @Override
  public void removeFromPortfolio(String removeStock) {
    Stock stock = getStock(removeStock);
    Stock[] newStocks = new Stock[stocks.length - 1];

    if (stock != null) {
      int newCounter = 0;
      for (int i = 0; i < stocks.length; i += 1) {
        if (!stocks[i].getTicker().equals(removeStock)) {
          newStocks[newCounter] = stocks[i];
          newCounter += 1;
        }
      }
      this.stocks = newStocks;
    }
  }

  @Override
  public void savePortfolio() throws IOException {
    File newFile = new File("saved_portfolios/" + this.getPortfolioTitle() + ".txt");
    FileWriter writer = new FileWriter(newFile);
    StringBuilder temporaryString = new StringBuilder();
    String finalBuy = "";
    String finalSell = "";
    for (Stock stock : stocks) {
      LocalDate nullBuySubstitute = stock.getBuyDate();
      LocalDate nullSellSubstitute = stock.getSellDate();
      if (stock.getBuyDate() != null) {
        finalBuy = nullBuySubstitute.toString();
      } else{
        finalBuy = "TBD";
      }
      if (stock.getSellDate() != null) {
        finalSell = nullSellSubstitute.toString() + "|";
      } else {
        finalSell = "TBD|";
      }
        temporaryString.append(stock.getTicker()).append(",").append(stock.getShares())
                .append(",").append(finalBuy).append(",").append(finalSell)
                .append(System.lineSeparator());

    }
    writer.write(temporaryString.toString());
    writer.close();
  }


  @Override
  public String getPortfolioOverTime(LocalDate dateOne, LocalDate dateTwo) {
    StringBuilder finalString = new StringBuilder();
    LocalDate newDate = dateOne;

    // First off find how many days between the two dates.
    int dateDistance = 1;
    while(newDate.isBefore(dateTwo)){
      newDate = newDate.plusDays(1);
      dateDistance += 1;
    }
    newDate = dateOne;

    // Then find the highest value date
    double highestValue = 0;
    for(int i = 0; i < dateDistance; i++){
      if(Double.parseDouble(this.getPortfolioValue(newDate)) > highestValue){
        highestValue = Double.parseDouble(this.getPortfolioValue(newDate));
      }
      newDate = newDate.plusDays(1);
    }
    highestValue = highestValue / 20;

    // Then add the info to the string with the data we've gathered!
    newDate = dateOne;
    for(int i = 0; i < dateDistance; i++){
      finalString.append(newDate.toString()).append(" - ");
      double stockValue = Double.parseDouble(this.getPortfolioValue(newDate));
      double starCount = stockValue/highestValue;
      StringBuilder stars = new StringBuilder();
      for(int j = 0; j < starCount; j++){
        stars.append("*");
      }
      finalString.append(stars).append(System.lineSeparator());
      newDate = newDate.plusDays(1);
    }
    finalString.append("* = ").append(highestValue);
    return finalString.toString();
  }

  @Override
  public int numberOfStocks() {
    return stocks.length;
  }

  @Override
  public Double getSharePriceOfStock(int stockIndex) {
    if (stockIndex < 0 || stockIndex >= stocks.length) {
      throw new IndexOutOfBoundsException();
    }

    String bigData = "";
    String[] separatedData;

    bigData = new AccessApi(stocks[stockIndex].getTicker())
            .returnData(LocalDate.now().toString(), LocalDate.now().toString());
    separatedData = bigData.split(",");

    // Only need the very end of the data
    String sharePrice = separatedData[separatedData.length - 2];

    return Double.valueOf(sharePrice);
  }

  @Override
  public Stock getStockByIndex(int stockIndex) {
    if (stockIndex < 0 || stockIndex >= stocks.length) {
      throw new IndexOutOfBoundsException();
    }

    return this.stocks[stockIndex];
  }

}
