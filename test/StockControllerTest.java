import org.junit.Before;
import org.junit.Test;

import controller.StockController;
import model.Model;
import model.ModelImpl;
import view.StockView;

import static org.junit.Assert.assertEquals;

public class StockControllerTest {
  StringBuilder out;
  Appendable ap;
  Model model;
  StockController stockController;
  StockView view;

  @Before
  public void setUp() {
    out = new StringBuilder();
    model = new ModelImpl();
    ap = out;
  }

  @Test
  public void testController() {

  }





}