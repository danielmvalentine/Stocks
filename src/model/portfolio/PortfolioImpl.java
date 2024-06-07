package model.portfolio;

import java.time.LocalDate;
import java.util.ArrayList;


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
    }

    return output;
  }

  @Override
  public String getPortfolioTitle() {
    return title;
  }

  @Override
  public String getPortfolioValue(LocalDate givenDate) {
    String bigData;
    String[] separatedData;
    StringBuilder output = new StringBuilder();

    for (Stock stock : stocks) {
      bigData = new AccessApi(stock.getTicker())
              .returnData(givenDate.toString(), givenDate.toString());
      separatedData = bigData.split(",");
      for (int i = 0; i < separatedData.length; i+= 1) {
        if (separatedData[i].equals(givenDate.toString())) {
          output.append(separatedData[i]);
          output.append(separatedData[i + 1]);
        }
      }
    }

    return output.toString();
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
