import java.io.InputStreamReader;

/**
 * The drivcer of this application.
 */
public class StockProgram {
  /**
   * Main method of the program.
   * @param args any command line arguments.
   */
  public static void main(String[] args) {
    IStock model = new SimpleStock();
    Readable rd = new InputStreamReader(System.in);
    StockView view = new StockView();
    StockController controller = new StockController(model, view, readable);
    controller.control();
  }
}
