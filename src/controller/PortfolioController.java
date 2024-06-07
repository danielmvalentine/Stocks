package controller;

import java.time.LocalDate;
import java.util.Scanner;

import model.Model;
import model.Stock;
import model.portfolio.PortfolioImpl;
import view.PortfolioView;
import view.StockView;

public class PortfolioController extends StockController {
  private boolean quit = false;
  PortfolioView view;
  Readable readable;


  public PortfolioController(Readable readable, PortfolioView view, Model model) {
    super(readable, view, model);
    this.view = view;
    this.readable = readable;
  }

  @Override
  public void processCommand(String userInput, Scanner scanner) {

    String title;
    String stock;
    int shares;

    switch (userInput) {
      case "create-portfolio":
        view.writeMessage("Enter a title for the portfolio: ");
        title = scanner.next();
        this.model.addPortfolio(new PortfolioImpl(title));
        break;
      case "list-portfolios":
        view.writeMessage("List of portfolios: " + this.model.formatPortfolios());
        break;
      case "add-stock-to":
        view.writeMessage("Enter the title of the portfolio the stock should be added to: ");
        title = scanner.next();
        view.writeMessage("Enter the stock to be added to the portfolio: ");
        stock = scanner.next();
        view.writeMessage("Enter the number of shares to be added: ");
        shares = scanner.nextInt();
        if (this.model.getPortfolio(title) != null) {
          this.model.getPortfolio(title).addToPortfolio(new Stock(stock, shares));
          view.writeMessage("Stock successfully added to the portfolio");
        } else {
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;
      case "remove-stock-from":
        view.writeMessage("Enter the title of the portfolio the stock should be removed from: ");
        title = scanner.next();
        view.writeMessage("Enter the stock to be removed from the portfolio: ");
        stock = scanner.next();
        if (this.model.getPortfolio(title) != null) {
          if (this.model.getPortfolio(title).getStock(stock) != null) {
            this.model.getPortfolio(title).removeFromPortfolio(stock);
            view.writeMessage("Stock removed from the portfolio." + System.lineSeparator());
          } else {
            view.writeMessage("Stock does not exist." + System.lineSeparator());
          }
        } else {
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;
      case "examine-portfolio":
        view.writeMessage("Enter the title of the portfolio to be examined: ");
        title = scanner.next();
        if (this.model.getPortfolio(title) != null) {
          view.writeMessage("List of stocks: " + this.model.getPortfolio(title).formatStock());
        } else {
          view.writeMessage("Portfolio does not exist." + System.lineSeparator());
        }
        break;
      case "get-value-of":
        view.writeMessage("Enter the title of the portfolio to be examined: ");
        title = scanner.next();
        view.writeMessage("Enter the date for the portfolio to be examined: ");
        LocalDate date = super.getDate(scanner);
        if (this.model.getPortfolio(title) != null) {
          view.writeMessage("Value of portfolio: "
                  + this.model.getPortfolio(title).getPortfolioValue(date));
        }
        break;
      case "b":
      case "B":
      case "back":
      case "Back":
        new StockController(this.readable, new StockView(System.out), this.model).control();
        quit = true;
        break;
      default:
        view.writeMessage("Invalid command: " + userInput + System.lineSeparator());
        this.control();
        break;
    }
  }
}
