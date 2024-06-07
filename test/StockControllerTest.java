import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import controller.StockController;
import model.MockModelImpl;
import model.Model;
import model.ModelImpl;
import view.StockView;

import static org.junit.Assert.assertEquals;

public class StockControllerTest {
  StringBuilder out;
  Appendable ap;
  Model model;
  StockController controller;
  StockView view;

  @Before
  public void setUp() {
    out = new StringBuilder();
    model = new ModelImpl();
    ap = out;
    view = new StockView(ap);
  }


  // TODO "2200\n1\n20\n" + // Tests invalid year - is valid???
  // TODO "-100\n2\n20\n" + // Tests negative year - is valid???

  @Test
  public void testInvalidDate() {
    Reader rd = new StringReader("xday-moving-average\nGOOG\n" +
            "2001\n2\n29\n" + // Tests invalid day
            "2001\n2\n-1\n" + // Tests negative day
            "2001\n15\n20\n" + // Tests invalid month
            "2001\n-1\n20\n" + // Tests negative month
            "2000\n2\n20\n" + // Valid year
            "Y\n6\nq" // Quitting the program
    );
    controller = new StockController(rd, view, model);
    controller.control();

    String[] expected = {"Invalid date: day=29 month=2 year=2001",
            "Invalid date: day=-1 month=2 year=2001",
            "Invalid date: day=20 month=15 year=2001",
            "Invalid date: day=20 month=-1 year=2001",};

    String[] output = ap.toString().split("\n");

    String invalidDates = output[10];

    String[] formattedOutput = new String[expected.length];

    for (int i = 0; i < expected.length; i++) {
      formattedOutput[i] = invalidDates.substring(invalidDates.indexOf("Invalid date: "),
              invalidDates.indexOf("year=") + 9);
      invalidDates = invalidDates.substring(invalidDates.indexOf("year=") + 9);
    }


    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i], formattedOutput[i]);
    }
  }

  @Test
  public void testControllerGainOrLoss() {
    Reader rd = new StringReader("stock-price-shift\nGOOG\n" +
            "2020\n2\n24\nY\n" + //First date
            "2020\n3\n3\nY\n" + // Second date
            "q" // Quitting the program

    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new StockController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("GainOrLoss: stock=GOOG initialDate=2020-02-24 finalDate=2020-03-03\n",
            log.toString());
  }

  @Test
  public void testControllerXDayMovingAverage() {
    Reader rd = new StringReader("xday-moving-average\nGOOG\n" +
            "2020\n2\n24\nY\n" + // Entering the date
            "30\nquit" // Entering the x value and quitting
    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new StockController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("movingAvg: stock=GOOG date=2020-02-24 xVal=30\n",
            log.toString());
  }

  @Test
  public void testControllerXDayCrossovers() {
    Reader rd = new StringReader("xday-crossovers\nGOOG\n" +
            "2020\n2\n24\nY\n" + // Entering the first date
            "2020\n3\n3\nY\n" + // Entering the second date
            "30\nquit" // Entering the x value and quitting
    );
    //Don't care about out

    StringBuilder log = new StringBuilder();
    Model mock = new MockModelImpl(log);

    controller = new StockController(rd, view, mock);
    controller.control();

    // Testing assign-value
    assertEquals("XDayCrossovers: stock=GOOG initialDate=2020-02-24 " +
                    "finalDate=2020-03-03 xVal=30\n",
            log.toString());
  }

}