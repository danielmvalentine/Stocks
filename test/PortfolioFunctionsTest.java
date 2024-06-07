import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.Model;
import model.ModelImpl;
import model.Stock;
import model.portfolio.IPortfolio;
import model.portfolio.PortfolioImpl;

import static org.junit.Assert.assertEquals;

/**
 * Contains the tests for the various Portfolio functions.
 */
public class PortfolioFunctionsTest {
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

    stock1 = new Stock("GOOG", 2);
    stock2 = new Stock("APPL", 3);
  }

  @Test
  public void testAddPortfolio() {
    m.addPortfolio(portfolio1);
    assertEquals(portfolio1, m.getPortfolio("portfolio1"));

    m.addPortfolio(portfolio2);
    assertEquals(portfolio1, m.getPortfolio("portfolio1"));
    assertEquals(portfolio2, m.getPortfolio("portfolio2"));
  }

  @Test
  public void testAddSamePortfolio() {
    assertEquals("No portfolios found", m.formatPortfolios());

    m.addPortfolio(portfolio1);
    assertEquals("\nportfolio1", m.formatPortfolios());

    m.addPortfolio(new PortfolioImpl("portfolio1"));
    assertEquals("\nportfolio1", m.formatPortfolios());
  }

  @Test
  public void testListPortfolios() {
    assertEquals("No portfolios found", m.formatPortfolios());

    m.addPortfolio(portfolio1);
    assertEquals("\nportfolio1: No stocks found", m.formatPortfolios());

    m.addPortfolio(portfolio2);
    assertEquals("\nportfolio1: No stocks found\n" +
            "portfolio2: No stocks found", m.formatPortfolios());

    portfolio1.addToPortfolio(stock1);
    assertEquals("\nportfolio1: \nGOOG 2.0\n" +
            "portfolio2: No stocks found", m.formatPortfolios());
  }

  @Test
  public void testAddStockToPortfolio() {
    m.addPortfolio(portfolio1);
    assertEquals("\nportfolio1: No stocks found", m.formatPortfolios());

    m.getPortfolio("portfolio1").addToPortfolio(stock1);
    assertEquals("\nportfolio1: \nGOOG 2.0", m.formatPortfolios());

    m.getPortfolio("portfolio1").addToPortfolio(stock2);
    assertEquals("\nportfolio1: \nGOOG 2.0\nAPPL 3.0", m.formatPortfolios());
  }

  @Test
  public void testRemoveStockFromPortfolio() {
    m.addPortfolio(portfolio1);
    m.getPortfolio("portfolio1").addToPortfolio(stock1);
    assertEquals("\nportfolio1: \nGOOG 2.0", m.formatPortfolios());

    // Won't remove anything if the stock to be removed isn't in the portfolio
    m.getPortfolio("portfolio1").removeFromPortfolio("APPL");
    assertEquals("\nportfolio1: \nGOOG 2.0", m.formatPortfolios());

    m.getPortfolio("portfolio1").addToPortfolio(stock2);
    assertEquals("\nportfolio1: \nGOOG 2.0\nAPPL 3.0", m.formatPortfolios());

    m.getPortfolio("portfolio1").removeFromPortfolio("GOOG");
    assertEquals("\nportfolio1: \nAPPL 3.0", m.formatPortfolios());

    m.getPortfolio("portfolio1").removeFromPortfolio("APPL");
    assertEquals("\nportfolio1: No stocks found", m.formatPortfolios());
  }

  @Test
  public void testExaminePortfolio() {
    m.addPortfolio(portfolio1);
    m.addPortfolio(portfolio2);
    assertEquals("No stocks found", m.getPortfolio("portfolio1").formatStock());

    m.getPortfolio("portfolio1").addToPortfolio(stock1);
    assertEquals("\nGOOG 2.0", m.getPortfolio("portfolio1").formatStock());

    m.getPortfolio("portfolio2").addToPortfolio(stock1);
    assertEquals("\nGOOG 2.0", m.getPortfolio("portfolio1").formatStock());

    m.getPortfolio("portfolio1").addToPortfolio(stock2);
    assertEquals("\nGOOG 2.0\nAPPL 3.0", m.getPortfolio("portfolio1").formatStock());
  }

  @Test
  public void testGetValueOfPortfolio() {
    LocalDate date = LocalDate.of(2024, 6, 6);

    m.addPortfolio(portfolio1);
    assertEquals("0.0", m.getPortfolio("portfolio1").getPortfolioValue(date));

    m.getPortfolio("portfolio1").addToPortfolio(stock1);
    assertEquals("", m.getPortfolio("portfolio1").getPortfolioValue(date));

    m.getPortfolio("portfolio1").addToPortfolio(stock2);
    assertEquals("", m.getPortfolio("portfolio1").getPortfolioValue(date));
  }
}
