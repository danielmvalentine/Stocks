package controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import model.PortfolioOptions;
import model.ProgramFunction;
import model.StockGainOrLoss;
import model.XDayCrossovers;
import model.XDayMovingAverage;
import view.StockView;

/**
 * Our basic controller that we use to represent
 */
public class StockController {

  private final Readable readable;
  StockView stockView;
  ProgramFunction function;

  public StockController(Readable readable, StockView stockView) {
    this.readable = readable;
    this.stockView = stockView;
  }

  /**
   * Our main method for our controller to edit maneuvering around our menu.
   */
  public void control(){
    Scanner scanner = new Scanner(readable);
    boolean quit = false;

    stockView.welcomeMessage();
    while(!quit && scanner.hasNext()){
      stockView.writeMessage("Input instruction: ");
      String userInput = scanner.nextLine();
      if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
        quit = true;
      } else {
        processCommand(userInput, scanner);
      }
    }

    stockView.leavingMessage();
  }

  public void processCommand(String userInput, Scanner scanner){
    String tag;
    LocalDate startDate;
    LocalDate endDate;
    int x;

    switch(userInput){
      case "stock-price-shift":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.nextLine();
        stockView.writeMessage("Enter the starting date for your desired time period: ");
        startDate = getDate(scanner);
        checkCorrectDate(scanner, startDate, userInput);
        stockView.writeMessage("Enter the ending date for your desired time period: ");
        endDate = getDate(scanner);
        checkCorrectDate(scanner, endDate, userInput);
        function = new StockGainOrLoss(tag, startDate, endDate);
        break;
      case "xday-moving-average":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.nextLine();
        stockView.writeMessage("Enter the date to begin the x-day moving average analysis on: ");
        String date = getDate(scanner);
        checkCorrectDate(scanner, date, userInput);
        stockView.writeMessage("Enter the number of days the analysis should be performed on: ");
        x = scanner.nextInt();
        function = new XDayMovingAverage(tag, date, x);
        break;
      case "xday-crossovers":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.nextLine();
        stockView.writeMessage("Enter the starting date for your desired time period: ");
        startDate = getDate(scanner);
        checkCorrectDate(scanner, startDate, userInput);
        stockView.writeMessage("Enter the ending date for your desired time period: ");
        endDate = getDate(scanner);
        checkCorrectDate(scanner, endDate, userInput);
        stockView.writeMessage("Enter the value of x for the x-day crossover value: ");
        x = scanner.nextInt();
        function = new XDayCrossovers(tag, startDate, endDate, x);
        break;
      case "portfolio":
        function = new PortfolioOptions();
        break;
    }

    function.execute();
  }

  private String getDate(Scanner scanner) {
    stockView.writeMessage("Enter year for the date: ");
    int year = scanner.nextInt();
    stockView.writeMessage("Enter month for the date: ");
    int month = scanner.nextInt();
    stockView.writeMessage("Enter day for the date: ");
    int day = scanner.nextInt();

    LocalDate date;

    try {
      date = LocalDate.of(year, month, day);
    } catch (DateTimeException e) {
      stockView.writeMessage("Invalid date: day: " + day
              + ", month: " + month + ", year: " + year );
      return getDate(scanner);
    }

    // In the format YYYY-MM-DD
    return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
  }

  private void checkCorrectDate(Scanner scanner, String date, String userInput) {
    stockView.writeMessage("Is this the correct date? " + date);
    stockView.writeMessage("Y/N: ");
    if(scanner.nextLine().equalsIgnoreCase("n")){
      stockView.writeMessage("Restarting...");
      processCommand(userInput, scanner);
    } else if (!scanner.nextLine().equalsIgnoreCase("y")){
      stockView.writeMessage("Invalid input, restarting...");
      processCommand(userInput, scanner);
    }
  }

}
