import javax.swing.*;

import controller.Controller;
import controller.PortfolioController;
import gui.StockProgramGUIFrame;
import model.Model;
import model.ModelImpl;

public class StockProgramGUI {
  public static void main(String[] args) {
    Model model = new ModelImpl();
    //Controller controller = new PortfolioController(model);
    StockProgramGUIFrame frame = new StockProgramGUIFrame(model);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
