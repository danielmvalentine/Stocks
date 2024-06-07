import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.PortfolioController;
import model.MockModelImpl;
import model.Model;
import model.ModelImpl;
import view.PortfolioView;

import static org.junit.Assert.assertEquals;

/**
 * Contains the tests for the portfolio controller.
 */
public class PortfolioControllerTest {
  StringBuilder out;
  Appendable ap;
  Model model;
  PortfolioController controller;
  PortfolioView view;

  @Before
  public void setUp() {
    out = new StringBuilder();
    model = new ModelImpl();
    ap = out;
    view = new PortfolioView(ap);
  }

  @Test
  public void testCreatePortfolio() {
    Reader rd = new StringReader("create-portfolio\nportfolio\n" +
            "b" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new PortfolioController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("addPortfolio: portfolio\n",
            log.toString());
  }

  @Test
  public void testListPortfolios() {
    Reader rd = new StringReader("list-portfolios\n" +
            "b" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new PortfolioController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("formatPortfolios: \n",
            log.toString());
  }

  @Test
  public void testAddStockTo() {
    Reader rd = new StringReader("create-portfolio\nportfolio\n" +
            "add-stock-to\nportfolio\nGOOG\n2\n" +
            "b" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new PortfolioController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("addPortfolio: portfolio\n" +
                    "getPortfolio: title=portfolio\n",
            log.toString());
  }

  @Test
  public void testRemoveStockFrom() {
    Reader rd = new StringReader("create-portfolio\nportfolio\n" +
            "remove-stock-from\nportfolio\nGOOG\n" +
            "b" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new PortfolioController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("addPortfolio: portfolio\n" +
                    "getPortfolio: title=portfolio\n",
            log.toString());
  }

  @Test
  public void testExaminePortfolio() {
    Reader rd = new StringReader("create-portfolio\nportfolio\n" +
            "examine-portfolio\nportfolio\n" +
            "b" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new PortfolioController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("addPortfolio: portfolio\n" +
                    "getPortfolio: title=portfolio\n",
            log.toString());
  }

  @Test
  public void testGetValueOf() {
    Reader rd = new StringReader("create-portfolio\nportfolio\n" +
            "get-value-of\nportfolio\n" +
            "2024\n6\n6\n" +
            "b" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new PortfolioController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("addPortfolio: portfolio\n" +
                    "getPortfolio: title=portfolio\n",
            log.toString());
  }

}