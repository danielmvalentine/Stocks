package model.functions;

import java.io.InputStreamReader;

import controller.PortfolioController;
import model.Model;
import view.PortfolioView;
import view.StockView;

/**
 * Public class for controlling the viewing and creation of Portfolio.
 */
public class PortfolioOptions implements ProgramFunction {

  Model model;
  StockView stockView;
  Readable rd;

  public PortfolioOptions(Model model, Readable rd, StockView view) {
    this.model = model;
    stockView = view;
    this.rd = rd;
  }

  /**
   * Our method that we will use to enter the portfolio creation and viewing section of the
   * program.
   *
   * @return Return a double formatted as a String for the average.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    PortfolioView view = new PortfolioView(stockView.appendable);
    PortfolioController controller = new PortfolioController(rd, view, this.model);
    controller.control();

    return "";
  }
}
