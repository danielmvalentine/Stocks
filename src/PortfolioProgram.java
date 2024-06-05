import java.io.InputStreamReader;

/**
 * The dirver of the Portfolio application.
 */
public class PortfolioProgram {
  /**
   * The main method of the Portfolio program.
   * @param args any command line arguments.
   */
  public static void main(String[] args) {
    IPortfolio model = new SimplePortfolio();
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    PortfolioController controller = new PortfolioController(model, rd, ap);
    controller.control();
  }
}
