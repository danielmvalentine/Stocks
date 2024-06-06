package controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.Model;
import model.functions.ProgramFunction;
import view.StockView;

/**
 * Our basic controller that we use to represent
 */
public class StockController {

  private final Readable readable;
  private final StockView stockView;
  protected final Model model;
  private boolean quit = false;

  public StockController(Readable readable, StockView stockView, Model model) {
    this.readable = readable;
    this.stockView = stockView;
    this.model = model;
  }

  /**
   * Our main method for our controller to edit maneuvering around our menu.
   */
  public void control(){
    Scanner scanner = new Scanner(readable);


    stockView.welcomeMessage();
    while(!quit){
      stockView.writeMessage(System.lineSeparator() + "Input instruction: ");
      String userInput = scanner.next();
      processCommand(userInput, scanner);
    }

    stockView.leavingMessage();
  }

  public void processCommand(String userInput, Scanner scanner){
    ProgramFunction function = null;

    String tag;
    LocalDate startDate;
    LocalDate endDate;
    int x;

    switch(userInput){
      case "stock-price-shift":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.next();
        stockView.writeMessage("Enter the starting date for your desired time period: ");
        startDate = getDate(scanner);
        checkCorrectDate(scanner, startDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the ending date for your desired time period: ");
        endDate = getDate(scanner);
        checkCorrectDate(scanner, endDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        function = model.gainOrLossOverTime(tag, startDate, endDate);
        break;
      case "xday-moving-average":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.next();
        stockView.writeMessage("Enter the date to begin the x-day moving average analysis on: ");
        LocalDate date = getDate(scanner);
        checkCorrectDate(scanner, date.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the number of days the analysis should be performed on: ");
        x = scanner.nextInt();
        function = model.movingAverage(tag, date, x);
        break;
      case "xday-crossovers":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.next();
        stockView.writeMessage("Enter the starting date for your desired time period: ");
        startDate = getDate(scanner);
        checkCorrectDate(scanner, startDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the ending date for your desired time period: ");
        endDate = getDate(scanner);
        checkCorrectDate(scanner, endDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the value of x for the x-day crossover value: ");
        x = scanner.nextInt();
        function = model.xDayCrossovers(tag, startDate, endDate, x);
        break;
      case "portfolio":
        function = model.portfolioOptions();
        break;
      case "q":
      case "Q":
      case "quit":
      case "Quit":
        quit = true;
        break;
      default:
        stockView.writeMessage("Invalid command: " + userInput + System.lineSeparator());
        control();
        break;
    }

    if (function != null) {
      function.execute();
    }
  }

  private LocalDate getDate(Scanner scanner) {
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
      stockView.writeMessage("Invalid date: day=" + day
              + " month=" + month + " year=" + year );
      return getDate(scanner);
    }

    return date;
  }

  private void checkCorrectDate(Scanner scanner, String date, String userInput) {
    stockView.writeMessage("Is this the correct date? " + date + System.lineSeparator());
    stockView.writeMessage("Y/N: ");
    String response = scanner.next();
    if(response.equalsIgnoreCase("n")){
      stockView.writeMessage("Restarting...");
      processCommand(userInput, scanner);
    } else if (!response.equalsIgnoreCase("y")){
      stockView.writeMessage("Invalid input, restarting...");
      processCommand(userInput, scanner);
    }
  }

}
