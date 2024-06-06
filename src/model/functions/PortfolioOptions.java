package model.functions;

import java.io.InputStreamReader;

import controller.PortfolioController;
import controller.StockController;
import model.Model;
import model.ModelImpl;
import view.PortfolioView;
import view.StockView;

/**
 * Public class for controlling the viewing and creation of Portfolio.
 */
public class PortfolioOptions implements ProgramFunction {

  Model model;

  public PortfolioOptions(Model model) {
    this.model = model;
  }

  /**
   * Our method that we will use to enter the portfolio creation and viewing section of the
   * program.
   *
   * @return Return a double formatted as a String for the average.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    Readable rd = new InputStreamReader(System.in);
    PortfolioView view = new PortfolioView(System.out);
    PortfolioController controller = new PortfolioController(rd, view, this.model);
    controller.control();

    return "";
  }
}
