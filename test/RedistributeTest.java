import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.Model;
import model.ModelImpl;
import model.Stock;
import model.portfolioProgram.IPortfolio;
import model.portfolioProgram.PortfolioImpl;
import model.portfolioProgram.portfolioFunctions.Redistribute;
import view.PortfolioView;

import static org.junit.Assert.*;

public class RedistributeTest {
  IPortfolio portfolio;

  Model m;
  PortfolioView view;
  StringBuilder out;
  Appendable ap;

  @Before
  public void setUp() {
    portfolio = new PortfolioImpl("portfolio");
    portfolio.addToPortfolio(new Stock("goog", 2,
            LocalDate.of(2012, 2, 2)));
    portfolio.addToPortfolio(new Stock("aapl", 6,
            LocalDate.of(2012, 2, 2)));

    m = new ModelImpl();
    m.addPortfolio(portfolio);

    out = new StringBuilder();
    ap = out;
    view = new PortfolioView(ap);
  }

  @Test
  public void testRedistribute1() {
    double[] redistribution = {75, 25};
    new Redistribute(m, view, redistribution, portfolio.getPortfolioTitle()).execute();

    assertEquals("\n  GOOG; 6.0 shares\n  AAPL; 2.0 shares",
            this.m.getPortfolio("portfolio").formatStock());
  }

  @Test
  public void testRedistribute2() {
    double[] redistribution = {100, 0};
    new Redistribute(m, view, redistribution, portfolio.getPortfolioTitle()).execute();

    assertEquals("\n  GOOG; 8.0 shares\n  AAPL; 0.0 shares",
            this.m.getPortfolio("portfolio").formatStock());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRedistributeTooMuchPercent() {
    double[] redistribution = {100, 1};
    new Redistribute(m, view, redistribution, portfolio.getPortfolioTitle()).execute();

    assertEquals("\n  GOOG; 8.0 shares\n  AAPL; 0.0 shares",
            this.m.getPortfolio("portfolio").formatStock());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRedistributeTooLittlePercent() {
    double[] redistribution = {98, 1};
    new Redistribute(m, view, redistribution, portfolio.getPortfolioTitle()).execute();

    assertEquals("\n  GOOG; 8.0 shares\n  AAPL; 0.0 shares",
            this.m.getPortfolio("portfolio").formatStock());
  }
}
