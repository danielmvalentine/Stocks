import java.io.InputStreamReader;

import controller.StockController;
import view.StockView;

/**
 * The drivcer of this application.
 */
public class StockProgram {
  /**
   * Main method of the program.
   * @param args any command line arguments.
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    StockView view = new StockView();
    StockController controller = new StockController(rd, view);
    controller.control();
  }
}
