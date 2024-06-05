import java.util.Date;

/**
 * This class represents a simple stock model. Provides information to the user about
 * given stocks and can create a portfolio with stock that will also provide information 
 * about itself.
 */
public class SimpleStock implements IStock {

  @Override
  public double gainOrLossOverTime(Stock stock, Date initialDate, Date finalDate) {

  }

  @Override
  public double movingAverage(Stock stock, Date date, int xValue) {

  }

  @Override
  public List<Date> xDayCrossovers(Stock stock, Date date, int xValue) {

  }

  //public void buildPortfolio(...){}
}
