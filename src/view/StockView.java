package view;

import java.io.IOException;

public class StockView {

  private final Appendable appendable;

  public StockView(Appendable appendable) {
    this.appendable = appendable;
  }

  /**
   * Public method writeMessage tries to write the message using a stringBuilder.
   *
   * @param message The message we pass in as a String.
   * @throws IllegalStateException If it is not possible to Append.
   */
  public void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Our method that writes the string that will be printed on the screen for our menu.
   */
  public void printMenu() {
    writeMessage(System.lineSeparator() + "Supported user instructions are: "
            + System.lineSeparator());
    writeMessage("1 - stock-price-shift - Examine the gain or loss of a stock over a specified period."
            + System.lineSeparator());
    writeMessage("2 - xday-moving-average - Examine the x-day moving average of a stock for a " +
            "specified date and a specified value of x."
            + System.lineSeparator());
    writeMessage("3 - xday-crossovers - Determine which days are x-day crossovers for a " +
            "specific stock over a specific date range and a specific value of x."
            + System.lineSeparator());
    writeMessage("4 - portfolio - Create or view a portfolio." + System.lineSeparator());
    writeMessage("Q or Quit - Quit the program." + System.lineSeparator());
  }

  /**
   * Simply prints our welcome message at the beginning of our application.
   */
  public void welcomeMessage() {
    writeMessage("Welcome to the Stocks Program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * Method for leaving the program.
   */
  public void leavingMessage() {
    writeMessage("Thank you for using this program!");
  }


}
