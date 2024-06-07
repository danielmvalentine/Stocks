package view;

public class PortfolioView extends StockView {

  public PortfolioView(Appendable appendable) {
    super(appendable);
  }

  @Override
  public void printMenu() {
    writeMessage(System.lineSeparator() + "Supported user instructions are: "
            + System.lineSeparator());
    writeMessage("create-portfolio - Create a new portfolio."
            + System.lineSeparator());
    writeMessage("list-portfolios - Lists the portfolios that have been created"
            + System.lineSeparator());
    writeMessage("add-stock-to - Add a stock to a specified portfolio."
            + System.lineSeparator());
    writeMessage("remove-stock-from - Remove a stock from a specified portfolio."
            + System.lineSeparator());
    writeMessage("examine-portfolio - Prints the stock and shares of stock within a portfolio."
            + System.lineSeparator());
    writeMessage("get-value-of - Gets the value of a specified portfolio."
            + System.lineSeparator());
    writeMessage("B or Back - Return to the main menu." + System.lineSeparator());
  }

  @Override
  public void welcomeMessage(){
    writeMessage(System.lineSeparator() + "The following are the portfolio options!" + System.lineSeparator());
    printMenu();
  }
}
