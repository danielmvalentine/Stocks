package view;

/**
 * Our public class for the view portion of our portfolio.
 */
public class PortfolioView extends StockView {

  Appendable appendable;

  /**
   * Constructor.
   *
   * @param appendable Dealing with scanner.
   */
  public PortfolioView(Appendable appendable) {
    super(appendable);
    this.appendable = appendable;
  }

  @Override
  public void printMenu() {
    writeMessage(System.lineSeparator() + "Supported user instructions are: "
            + System.lineSeparator());
    writeMessage("1 - create-portfolio - Create a new portfolio."
            + System.lineSeparator());
    writeMessage("2 - list-portfolios - Lists the portfolios that have been created"
            + System.lineSeparator());
    writeMessage("3 - add-stock-to - Add a stock to a specified portfolio."
            + System.lineSeparator());
    writeMessage("4 - remove-stock-from - Remove a stock from a specified portfolio."
            + System.lineSeparator());
    writeMessage("5 - examine-portfolio - Prints the stock and shares of stock within a portfolio."
            + System.lineSeparator());
    writeMessage("6 - get-value-of - Gets the value of a specified portfolio."
            + System.lineSeparator());
    writeMessage("7 - save-portfolio - Saves the specified portfolio to the computer."
            + System.lineSeparator());
    writeMessage("B or Back - Return to the main menu." + System.lineSeparator());
  }

  @Override
  public void welcomeMessage() {
    writeMessage(System.lineSeparator() + "The following are the portfolio options!"
            + System.lineSeparator());
  }
}
