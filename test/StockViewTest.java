import org.junit.Before;
import org.junit.Test;

import view.StockView;

import static org.junit.Assert.assertEquals;

public class StockViewTest {
  StockView view;
  StringBuilder out;
  Appendable ap;

  @Before
  public void setUp() {
    out = new StringBuilder();
    ap = out;
    view = new StockView(ap);
  }

  @Test
  public void testStockWelcomeMessage() {
    view.welcomeMessage();
    String output = ap.toString();

    assertEquals("Welcome to the Stocks Program!\n", output);
  }

  @Test
  public void testStockMenu() {
    view.printMenu();
    String output = ap.toString();

    assertEquals("\nSupported user instructions are: \n" +
                    "1 - stock-price-shift - Examine the gain or loss of a stock " +
                    "over a specified period.\n" +
                    "2 - xday-moving-average - Examine the x-day moving average of a " +
                    "stock for a specified date and a specified value of x.\n" +
                    "3 - xday-crossovers - Determine which days are x-day crossovers for a " +
                    "specific stock over a specific date range and a specific value of x.\n" +
                    "4 - portfolio - Create or view a portfolio.\n" +
                    "Q or Quit - Quit the program.\n",
            output);
  }

  @Test
  public void testStockLeavingMessage() {
    view.leavingMessage();
    String output = ap.toString();

    assertEquals("Thank you for using this program!", output);
  }
}