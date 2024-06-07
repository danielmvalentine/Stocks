package model.portfolio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


import model.AccessApi;
import model.Stock;

public class PortfolioImpl implements IPortfolio {
  private final String title;
  private ArrayList<Stock> stocks = new ArrayList<Stock>();

  public PortfolioImpl(String title) {
    this.title = title;
  }

  @Override
  public Stock getStock(String ticker) {
    for(Stock stock : stocks) {
      if (stock.getTicker().equals(ticker)) {
        return stock;
      }
    }
    return null;
  }

  @Override
  public String formatStock() {
    if (stocks.isEmpty()) {
      return "No stocks found";
    }

    String output = "";

    for (Stock stock : stocks) {
      output += "\n";
      output += stock.getTicker();
      output += " " + stock.getShares();
    }

    return output;
  }

  @Override
  public String getPortfolioTitle() {
    return title;
  }

  @Override
  public String getPortfolioValue(LocalDate givenDate) {
    String bigData = "";
    String[] separatedData;
    double value = 0;
    String date = givenDate.format(DateTimeFormatter.ISO_LOCAL_DATE);

    for (Stock stock : stocks) {
      bigData = new AccessApi(stock.getTicker())
              .returnData(givenDate.toString(), givenDate.toString());
      separatedData = bigData.split(",");
      if (bigData.contains(date)) {
        for (int i = 0; i < separatedData.length; i += 5) {
          if(separatedData[i].contains(date)) {
            value += (stock.getShares() * Double.parseDouble(separatedData[i + 4]));
            break;
          }
        }
      }
    }


    return String.valueOf(value);
  }

  @Override
  public void addToPortfolio(Stock inputStock) {
    this.stocks.add(inputStock);
  }

  @Override
  public void removeFromPortfolio(String removeStock) {
    Stock stock = getStock(removeStock);
    if (stock != null && this.stocks.contains(stock)) {
      this.stocks.remove(stock);
    }
  }
}
