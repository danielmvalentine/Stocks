import java.io.IOException;
import java.io.InputStreamReader;

import controller.StockController;
import model.Model;
import model.ModelImpl;
import view.StockView;

/**
 * The driver of this application.
 */
public class StockProgram {
  /**
   * Main method of the program.
   *
   * @param args any command line arguments.
   */
  public static void main(String[] args) throws IOException {
    Readable rd = new InputStreamReader(System.in);
    StockView view = new StockView(System.out);
    Model model = new ModelImpl();
    StockController controller = new StockController(rd, view, model);
    controller.control();
  }
}
