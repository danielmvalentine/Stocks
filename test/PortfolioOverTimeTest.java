import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.Model;
import model.ModelImpl;
import model.Stock;
import model.portfolio.*;

import static org.junit.Assert.assertEquals;

public class PortfolioOverTimeTest {

  Model m;
  IPortfolio portfolio1;
  IPortfolio portfolio2;

  Stock stock1;
  Stock stock2;

  @Before
  public void setUp() {
    m = new ModelImpl();

    portfolio1 = new PortfolioImpl("portfolio1");
    portfolio2 = new PortfolioImpl("portfolio2");

    stock1 = new Stock("GOOG", 2, LocalDate.of(2012, 2, 2));
    stock2 = new Stock("APPL", 3, LocalDate.of(2012, 2, 2));
  }

  @Test
  public void testPortfolioOverTimeOne() {
    IPortfolio portfolio = portfolio1;
    portfolio.addToPortfolio(stock1);
    LocalDate dateOne = LocalDate.of(2015, 5, 1);
    LocalDate dateTwo = LocalDate.of(2015, 5, 5);
    String finalChart = portfolio.getPortfolioOverTime(dateOne, dateTwo);
    assertEquals("2015-05-01 - ********************\n" +
            "2015-05-02 - ********************\n" +
            "2015-05-03 - ********************\n" +
            "2015-05-04 - ********************\n" +
            "2015-05-05 - ********************\n" +
            "* = $54.08", finalChart);
  }

  @Test
  public void testPortfolioOverTimeTwo() {
    IPortfolio portfolio = portfolio1;
    portfolio.addToPortfolio(stock1);
    LocalDate dateOne = LocalDate.of(2015, 5, 1);
    LocalDate dateTwo = LocalDate.of(2015, 5, 20);
    String finalChart = portfolio.getPortfolioOverTime(dateOne, dateTwo);
    assertEquals("2015-05-01 - ********************\n" +
            "2015-05-02 - ********************\n" +
            "2015-05-03 - ********************\n" +
            "2015-05-04 - ********************\n" +
            "2015-05-05 - ********************\n" +
            "2015-05-06 - ********************\n" +
            "2015-05-07 - ********************\n" +
            "2015-05-08 - ********************\n" +
            "2015-05-09 - ********************\n" +
            "2015-05-10 - ********************\n" +
            "2015-05-11 - ********************\n" +
            "2015-05-12 - ********************\n" +
            "2015-05-13 - ********************\n" +
            "2015-05-14 - ********************\n" +
            "2015-05-15 - ********************\n" +
            "2015-05-16 - ********************\n" +
            "2015-05-17 - ********************\n" +
            "2015-05-18 - ********************\n" +
            "2015-05-19 - ********************\n" +
            "2015-05-20 - ********************\n" +
            "* = $54.08", finalChart);
  }

}
