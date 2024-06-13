package model.portfolio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    for (Stock stock : stocks) {
      if (stock.getTicker().equals(ticker)) {
        return stock;
      }
    }
    return null;
  }

  @Override
  public String formatStock() {
    if (stocks.length == 0) {
      return "No stocks found";
    }

    String output = "";

    for (Stock stock : stocks) {
      if (stock.getSellDate() == null) {
        output += "\n";
        output += stock.getTicker();
        output += "; " + stock.getShares() + " shares";

      }
    }

    return output;
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
        if (stock.getBuyDate().isBefore(givenDate) && stock.getSellDate().isAfter(givenDate)) {
          // Gets the data for the given stock
          bigData = new AccessApi(stock.getTicker())
                  .returnData(givenDate.toString(), givenDate.toString());
          separatedData = bigData.split(",");
          if (bigData.contains(date)) {
            // Locates the given date and adds the end of day value per stock times its shares.
            for (int i = 0; i < separatedData.length; i += 5) {
              if (separatedData[i].contains(date)) {
                value += (stock.getShares() * Double.parseDouble(separatedData[i + 4]));
                break;
              }
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
    String output = "";

    for (Stock stock : stocks) {
      if (stock.getBuyDate().isBefore(givenDate) && stock.getSellDate().isAfter(givenDate)) {
        // Gets the data for the given stock
        bigData = new AccessApi(stock.getTicker())
                .returnData(givenDate.toString(), givenDate.toString());
        separatedData = bigData.split(",");
        if (bigData.contains(date)) {
          // Locates the given date and adds the end of day value per stock times its shares.
          for (int i = 0; i < separatedData.length; i += 5) {
            if (separatedData[i].contains(date)) {
              output += System.lineSeparator();
              output += stock.getTicker() + ": $";
              output += stock.getShares() * Double.parseDouble(separatedData[i + 4]);
              break;
            }
          }
        }
      }
    }

    return output;

  }

  @Override
  public void addToPortfolio(Stock inputStock) {
    Stock[] newStocks = new Stock[stocks.length + 1];
    for (int i = 0; i < stocks.length; i++) {
      newStocks[i] = stocks[i];
    }
    newStocks[newStocks.length - 1] = inputStock;
    this.stocks = newStocks;
  }

  @Override
  public void removeFromPortfolio(String removeStock) {
    Stock stock = getStock(removeStock);
    Stock[] newStocks = new Stock[stocks.length - 1];
    if (stock != null) {
      for (int i = 0; i < stocks.length; i += 1) {
        if (stocks[i].getTicker().equals(removeStock)) {
          i += 1;
        } else {
          newStocks[i] = stocks[i];
        }
      }
      this.stocks = newStocks;
    }
  }

  @Override
  public void savePortfolio() throws IOException {
    File newFile = new File("saved_portfolios/" + this.getPortfolioTitle() + ".txt");
    FileWriter writer = new FileWriter(newFile);
    String temporaryString = "";
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
        temporaryString = temporaryString + stock.getTicker() + ","
                + stock.getShares() + "," + finalBuy + ","
                + finalSell + System.lineSeparator();

    }
    writer.write(temporaryString);
    writer.close();
  }


  @Override
  public void getPortfolioOverTime(LocalDate dateOne, LocalDate dateTwo) {

  }
}
