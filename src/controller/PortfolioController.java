package controller;

import java.time.LocalDate;
import java.util.Scanner;

import model.Model;
import model.Stock;
import model.portfolio.PortfolioImpl;
import view.PortfolioView;

public class PortfolioController extends StockController {
  private boolean quit = false;
  PortfolioView view;


  public PortfolioController(Readable readable, PortfolioView view, Model model) {
    super(readable, view, model);
  }

  @Override
  public void processCommand(String userInput, Scanner scanner){

    String title;
    String stock;
    LocalDate endDate;
    int x;

    switch(userInput){
      case "create-portfolio":
        view.writeMessage("Enter a title for the portfolio: ");
        title = scanner.next();
        this.model.addPortfolio(new PortfolioImpl(title));
        break;
      case "list-portfolios":
        view.writeMessage("List of portfolios:" + this.model.formatPortfolios());
        break;
      case "add-stock-to":
        view.writeMessage("Enter the title of the portfolio the stock should be added to: ");
        title = scanner.next();
        view.writeMessage("Enter the stock to be added to the portfolio: ");
        stock = scanner.next();
        view.writeMessage("Enter the number of shares to be added: ");
        int shares = scanner.nextInt();
        if (this.model.getPortfolio(title) != null) {
          this.model.getPortfolio(title).addToPortfolio(new Stock(stock, shares));
        } else {
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
      }
        break;
      case "remove-stock-from":

        break;
      case "b":
      case "B":
      case "back":
      case "Back":
        quit = true;
        break;
      default:
        view.writeMessage("Invalid command: " + userInput + System.lineSeparator());
        control();
        break;
    }
  }
}
