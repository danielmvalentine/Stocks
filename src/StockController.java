import java.util.Scanner;

/**
 * Represents a controller of an interactive stock program that has a variety
 * of different functionality. 
 */
public class StockController {
  private IStock stock;
  private StockView view;
  private Readable readable;

  /**
   * Creates a controller to work with the specified stock.
   */
  public StockController(IStock stock, StockView view, Readable readable) {
    if ((stock == null) || (view == null) || (readable == null)) {
      throw new IllegalArgumentException("Stock, view, or readable is null");
    }

    this.stock = stock;
    this.view = view;
    this.readable = readable;
  }

  public void control() {
    Scanner sc = new Scanner(readable);
    boolean quit = false;

    // Prints welcome message
    this.welcomeMessage();

    // TODO processing commands and such

    // After the user quits, print the farewell message
    this.farewellMessage
  }


  
  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the stocks program!" + System.lineSeparator());
    printMenu();
  }

  
  protected void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }
  
}
