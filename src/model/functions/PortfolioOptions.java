package model.functions;

import controller.PortfolioController;
import model.Model;
import view.PortfolioView;
import view.StockView;

/**
 * Public class that holds the functions for the viewing and creation of Portfolios.
 */
public class PortfolioOptions implements ProgramFunction {
  Model model;
  StockView stockView;
  Readable rd;

  /**
   * Creates a new PortfolioOptions Object.
   *
   * @param model The current model for the stocks program.
   * @param rd    The input of the portfolio controller.
   * @param view  Transmits the output of the portfolio controller.
   */
  public PortfolioOptions(Model model, Readable rd, StockView view) {
    this.model = model;
    this.stockView = view;
    this.rd = rd;
  }

  /**
   * Our method that we will use to enter the portfolio creation and viewing section of the
   * program.
   *
   * @return An empty String as this function is slightly different from the others.
   */
  @Override
  public String execute() throws IllegalArgumentException {
    PortfolioView view = new PortfolioView(stockView.appendable);
    PortfolioController controller = new PortfolioController(rd, view, this.model);
    // Starts the portfolio-centric interface.
    controller.control();

    return "";
  }
}
