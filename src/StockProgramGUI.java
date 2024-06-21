
import javax.swing.JFrame;

import gui.StockProgramGUIFrame;
import model.Model;
import model.ModelImpl;

/**
 * The driver of the GUI-based application for the Stocks Program.
 */
public class StockProgramGUI {
  /**
   * The main method of the GUI for the Stock Program.
   *
   * @param args any command line arguments.
   */
  public static void main(String[] args) {
    Model model = new ModelImpl();
    //Controller controller = new PortfolioController(model);
    StockProgramGUIFrame frame = new StockProgramGUIFrame(model);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
