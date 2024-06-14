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
    writeMessage("2 - list-portfolios - Lists the current portfolios and their current information"
            + System.lineSeparator());
    writeMessage("3 - add-stock-to - Add a stock to a specified portfolio."
            + System.lineSeparator());
    writeMessage("4 - sell-stock-from - Remove a stock from a specified portfolio."
            + System.lineSeparator());
    writeMessage("5 - examine-portfolio - Prints the stock and shares of stock within a portfolio."
            + System.lineSeparator());
    writeMessage("6 - get-value-of - Gets the value of a specified portfolio."
            + System.lineSeparator());
    writeMessage("7 - distribution of value - Returns the value of each stock in the portfolio."
            + System.lineSeparator());
    writeMessage("8 - save-portfolio - Saves the specified portfolio to the computer."
            + System.lineSeparator());
    writeMessage("9 - load-portfolio - Loads the specified portfolio. (Portfolio must be located" +
            "in the given folder titled 'saved_portfolios'" + System.lineSeparator());
    writeMessage("10 - find-portfolio-over-time - Prints out a graph to show "
            + "the distribution of values over time for the given portfolio"
            + System.lineSeparator());
    writeMessage("11 - redistribute-portfolio - Redistributes the given portfolio based on "
            + "rebalancing the number of shares within the portfolio"
            + System.lineSeparator());
    writeMessage("B or Back - Return to the main menu." + System.lineSeparator());
  }

  @Override
  public void welcomeMessage() {
    writeMessage(System.lineSeparator() + "The following are the portfolio options!"
            + System.lineSeparator());
  }
}
