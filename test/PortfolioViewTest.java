import org.junit.Before;
import org.junit.Test;

import view.PortfolioView;

import static org.junit.Assert.assertEquals;

/**
 * Contains the tests for the Portfolio view.
 */
public class PortfolioViewTest {
  PortfolioView view;
  StringBuilder out;
  Appendable ap;

  @Before
  public void setUp() {
    out = new StringBuilder();
    ap = out;
    view = new PortfolioView(ap);
  }

  @Test
  public void testPortfolioWelcomeMessage() {
    view.welcomeMessage();
    String output = ap.toString();

    assertEquals("\nThe following are the portfolio options!\n", output);
  }

  @Test
  public void testPortfolioMenu() {
    view.printMenu();
    String output = ap.toString();

    assertEquals("\nSupported user instructions are: \n" +
                    "1 - create-portfolio - Create a new portfolio.\n" +
                    "2 - list-portfolios - Lists the current portfolios " +
                    "and their current information\n" +
                    "3 - add-stock-to - Add a stock to a specified portfolio.\n" +
                    "4 - sell-stock-from - Remove a stock from a specified portfolio.\n" +
                    "5 - examine-portfolio - Prints the stock and shares of stock " +
                    "within a portfolio.\n" +
                    "6 - get-value-of - Gets the value of a specified portfolio.\n" +
                    "7 - distribution of value - " +
                    "Returns the value of each stock in the portfolio.\n" +
                    "8 - save-portfolio - Saves the specified portfolio to the computer.\n" + "" +
                    "9 - load-portfolio - Loads the specified portfolio. " +
                    "(Portfolio must be locatedin the given folder titled 'saved_portfolios'\n" +
                    "10 - find-portfolio-over-time - Prints out a graph to show the " +
                    "distribution of values over time for the given portfolio\n" +
                    "11 - redistribute-portfolio - Redistributes the given portfolio " +
                    "based on rebalancing the number of shares within the portfolio\n" +
                    "B or Back - Return to the main menu.\n",
            output);
  }
}