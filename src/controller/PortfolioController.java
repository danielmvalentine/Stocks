package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import model.Model;

import model.portfolio.*;
import model.portfolio.portfolioFunctions.AddStockTo;
import model.portfolio.portfolioFunctions.SellStockFrom;
import view.PortfolioView;

/**
 * This class represents the controller of an interactive portfolio program within
 * the stocks program. It utilizes a text-based interface, with the user being able to type
 * commands to examine portfolios.
 *
 * <p>This controller works with any Readable to read its inputs and
 * any Appendable to transmit output through a viewer.
 */
public class PortfolioController extends StockController {
  private boolean quit;
  PortfolioView view;
  Readable readable;


  /**
   * Creates a new PortfolioController object of the StockProgram. Handles user input
   * for portfolios and tells the model, portfolios, and stocks.
   *
   * @param readable The input of the controller.
   * @param view     Transmits the output of the controller.
   * @param model    The functionality of the controller.
   */
  public PortfolioController(Readable readable, PortfolioView view, Model model) {
    super(readable, view, model);
    this.view = view;
    this.readable = readable;
    this.quit = false;
  }


  /**
   * Code reuse from StockController due to separate quit commands.
   * Our main method for our controller to edit maneuvering around our menu.
   * Will iterate until the user quits the portfolio program.
   */
  public void control() throws IOException {
    Scanner scanner = new Scanner(readable);

    view.welcomeMessage();
    while (!this.quit) {
      view.printMenu();
      view.writeMessage(System.lineSeparator() + "Input instruction: ");
      String userInput = scanner.next();
      processCommand(userInput, scanner);
    }

    quit = true;
  }

  // Processes all available commands.
  private void processCommand(String userInput, Scanner scanner) throws IOException {

    String title;
    String stock;
    int shares;
    LocalDate date;

    switch (userInput) {
      case "1":
      case "create-portfolio":
        view.writeMessage("Enter a title for the portfolio: ");
        title = scanner.next();

        // Tells the model to add a new portfolio
        this.model.addPortfolio(new PortfolioImpl(title));
        break;


      case "2":
      case "list-portfolios":
        // Tells the model to format its portfolios
        view.writeMessage("List of portfolios: " + this.model.formatPortfolios());
        break;


      case "3":
      case "add-stock-to":
        view.writeMessage("Enter the title of the portfolio the stock should be added to: ");
        title = scanner.next();
        view.writeMessage("Enter the stock to be added to the portfolio: ");
        stock = scanner.next();
        view.writeMessage("Enter the number of shares to be added: ");
        shares = scanner.nextInt();
        view.writeMessage("Enter the date the stock was added: ");
        date = super.getDate(scanner);

        new AddStockTo(model, view, title, stock, shares, date).execute();
        break;


      // Will remove all stock if the shares entered are greater than the shares present
      case "4":
      case "remove-stock-from":
        view.writeMessage("Enter the title of the portfolio the stock should be sold from: ");
        title = scanner.next();
        view.writeMessage("Enter the stock to be sold from the portfolio: ");
        stock = scanner.next();
        view.writeMessage("Enter the number of shares to be sold: ");
        shares = scanner.nextInt();
        view.writeMessage("Enter the date the stock should be sold on: ");
        date = super.getDate(scanner);

        new SellStockFrom(model, view, title, stock, shares, date).execute();
        break;


      case "5":
      case "examine-portfolio":
        view.writeMessage("Enter the title of the portfolio to be examined: ");
        title = scanner.next();
        if (this.model.getPortfolio(title) != null) {
          // Formats the stocks of the portfolio
          view.writeMessage("List of stocks: " + this.model.getPortfolio(title).formatStock());
        } else {
          // If the portfolio doesn't exist, informs the user and resets.
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;

      case "6":
      case "get-value-of":
        view.writeMessage("Enter the title of the portfolio to be examined: ");
        title = scanner.next();
        view.writeMessage("Enter the date for the portfolio to be examined: ");
        date = super.getDate(scanner);
        if (this.model.getPortfolio(title) != null) {
          // Calculates the value of the portfolio
          view.writeMessage("Value of portfolio: "
                  + this.model.getPortfolio(title).getPortfolioValue(date));
        } else {
          // If the portfolio doesn't exist, informs the user and resets.
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;


      case "7":
      case "distribution-of-value":
        view.writeMessage("Enter the title of the portfolio to be examined: ");
        title = scanner.next();
        view.writeMessage("Enter the date for the portfolio to be examined: ");
        date = super.getDate(scanner);
        if (this.model.getPortfolio(title) != null) {
          // Calculates the value of the portfolio
          view.writeMessage("Value of portfolio: "
                  + this.model.getPortfolio(title).distributionOfValue(date));
        } else {
          // If the portfolio doesn't exist, informs the user and resets.
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;


      case "8":
      case "save-portfolio":
        view.writeMessage("Enter the title of the portfolio to be saved: ");
        title = scanner.next();
        if (this.model.getPortfolio(title) != null) {
          //Saves the portfolio
          this.model.getPortfolio(title).savePortfolio();
        } else {
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;

      case "8":
      case "load-portfolio":
        view.writeMessage("Enter the title of the file of the portfolio to be loaded: "
                + System.lineSeparator() + "Ex: portfolio.txt");
        title = scanner.next();
        String titleOfPortfolio = new String("");
        for(int i = 0; i < title.length() - 5; i++){
          titleOfPortfolio = titleOfPortfolio + title.charAt(i);
        }
        PortfolioImpl newPortfolio = new PortfolioImpl(titleOfPortfolio);
        this.model.getPortfolio(titleOfPortfolio).getFromTxt(titleOfPortfolio, title);
        break;

      case "9":
      case "find-portfolio-over-time":
        view.writeMessage("Enter the name of the portfolio to be examined: ");
        title = scanner.next();
        view.writeMessage("Enter the date you would like the beginning "
                + "of the examination to begin on: ");
        LocalDate dateOne = getDate(scanner);
        view.writeMessage("Enter the date you would like the end of "
                + "the examination to happen on: ");
        LocalDate dateTwo = getDate(scanner);
        if (this.model.getPortfolio(title) != null) {
          this.model.getPortfolio(title).getPortfolioOverTime(dateOne, dateTwo);
        } else {
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;


      case "b":
      case "B":
      case "back":
      case "Back":
        this.quit = true;
        break;


      default:
        // Will reset the controller to allow the user to correct their command
        view.writeMessage("Invalid command: " + userInput + System.lineSeparator());
        this.control();
        break;
    }
  }

}
