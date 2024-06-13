package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import model.portfolio.IPortfolio;
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

  ArrayList<IPortfolio> portfolios = new ArrayList<IPortfolio>();


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
  public ArrayList<IPortfolio> getAllPortfolios() {
    return portfolios;
  }


  @Override
  public String formatPortfolios() {
    if (portfolios.isEmpty()) {
      return "No portfolios found";
    }

    String output = "";

    for (IPortfolio portfolio : portfolios) {
      output += "\n";
      output += portfolio.getPortfolioTitle();
      output += ": " + portfolio.formatStock();
    }

    return output;
  }

  @Override
  public void addPortfolio(IPortfolio portfolio) {
    if (!sameTitle(portfolios, portfolio.getPortfolioTitle())) {
      portfolios.add(portfolio);
    }
  }

  private boolean sameTitle(ArrayList<IPortfolio> portfolios, String title) {
    for (IPortfolio portfolio : portfolios) {
      if (portfolio.getPortfolioTitle().equals(title)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void getFromTxt(String title, String filePath) throws IOException {
    String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
    ArrayList<String> listOfStockNames = new ArrayList<>();
    double[] listOfStockValues = new double[fileContent.length()];
    boolean isAtTag = false;
    boolean isAtValue = false;
    int acc = 0;
    for(int i = 0; i < fileContent.length(); i++) {

    }
  }
}
