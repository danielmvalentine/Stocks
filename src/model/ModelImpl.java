package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.portfolio.IPortfolio;
import model.portfolio.*;
import model.stockFunctions.PortfolioOptions;
import model.stockFunctions.StockFunction;
import model.stockFunctions.StockGainOrLoss;
import model.stockFunctions.XDayCrossovers;
import model.stockFunctions.XDayMovingAverage;
import view.StockView;

/**
 * Is an implementation of the Model interface. Contains the portfolios of the user, and
 * the functions that can be done by the program.
 */
public class ModelImpl implements Model {

  IPortfolio[] portfolios;


  @Override
  public StockFunction gainOrLossOverTime(String stockName,
                                          LocalDate initialDate, LocalDate finalDate) {
    return new StockGainOrLoss(stockName, initialDate, finalDate);
  }

  @Override
  public StockFunction movingAverage(String stockName, LocalDate date, int xValue) {
    return new XDayMovingAverage(stockName, date, xValue);
  }

  @Override
  public StockFunction xDayCrossovers(String stockName,
                                        LocalDate initialDate, LocalDate finalDate, int xValue) {
    return new XDayCrossovers(stockName, initialDate, finalDate, xValue);
  }

  @Override
  public StockFunction portfolioOptions(Readable rd, StockView view) {
    return new PortfolioOptions(this, rd, view);
  }

  @Override
  public IPortfolio getPortfolio(String title) {
    for (IPortfolio portfolio : portfolios) {
      if (portfolio.getPortfolioTitle().equals(title)) {
        return portfolio;
      }
    }
    return null;
  }

  @Override
  public IPortfolio[] getAllPortfolios() {
    return portfolios;
  }


  @Override
  public String formatPortfolios() {
    if (portfolios.length == 0) {
      return "No portfolios found";
    }

    StringBuilder output = new StringBuilder();

    for (IPortfolio portfolio : portfolios) {
      output.append("\n");
      output.append(portfolio.getPortfolioTitle());
      output.append(": ").append(portfolio.formatStock());
    }

    return output.toString();
  }

  @Override
  public void addPortfolio(IPortfolio portfolio) {
    if (portfolios == null) {
      portfolios = new IPortfolio[1];
      portfolios[0] = portfolio;
    } else if (!sameTitle(portfolios, portfolio.getPortfolioTitle())) {
      IPortfolio[] newList = new IPortfolio[portfolios.length + 1];
      System.arraycopy(portfolios, 0, newList, 0, newList.length - 1);
      newList[newList.length - 1] = portfolio;
      this.portfolios = newList;
    }
  }

  private boolean sameTitle(IPortfolio[] portfolios, String title) {
    if (portfolios == null) {
      return false;
    }
    for (IPortfolio portfolio : portfolios) {
      if (portfolio.getPortfolioTitle().equals(title)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void getFromTxt(String title, String filePath) throws IOException {
    try {
      File file = new File(filePath);
      FileInputStream fis = new FileInputStream(file);
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);
      String lineContent;
      String fileContent = "";
      while((lineContent = br.readLine()) != null) {
       fileContent = fileContent + lineContent;
      }
      br.close();
      ArrayList<String> listOfStockNames = new ArrayList<>();
      double[] listOfStockValues = new double[fileContent.length()];
      // How many stocks do we need to add to the portfolio?
      int acc = 0;
      String[] stockInfo = fileContent.split(System.lineSeparator());
      IPortfolio newPortfolio = new PortfolioImpl(title);
      for (int i = 0; i < stockInfo.length; i++) {
        String[] individualStockInfo = stockInfo[i].split(",");
        String ticker = individualStockInfo[0];
        double shares = Double.parseDouble(individualStockInfo[1]);
        String date = individualStockInfo[2];
        LocalDate individualDate = LocalDate.parse(date);
        Stock newStock = new Stock(ticker, shares, individualDate);
        newPortfolio.addToPortfolio(newStock);
      }
    }catch(NoSuchFileException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int numberOfPortfolios() {
    if (portfolios == null) {
      return 0;
    }
    return portfolios.length;
  }
}
