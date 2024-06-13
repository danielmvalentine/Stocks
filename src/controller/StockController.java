package controller;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.Model;
import model.stockFunctions.StockFunction;
import view.StockView;

/**
 * This class represents the controller of an interactive stocks program.
 * It utilizes a text-based interface, with the user being able to type
 * commands to examine stocks.
 *
 * <p>This controller works with any Readable to read its inputs and
 * any Appendable to transmit output through a viewer.
 */
public class StockController {

  private final Readable readable;
  private final StockView stockView;
  protected final Model model;
  private boolean quit;

  /**
   * Creates a new StockController object of the StockProgram. Handles user input and
   * tells the model to do what when.
   *
   * @param readable  The input of the controller.
   * @param stockView Transmits the output of the controller.
   * @param model     The functionality of the controller.
   */
  public StockController(Readable readable, StockView stockView, Model model) {
    this.readable = readable;
    this.stockView = stockView;
    this.model = model;
    this.quit = false;
  }

  /**
   * Our main method for our controller to edit maneuvering around our menu.
   * Will iterate until the user quits the program.
   */
  public void control() throws IOException {
    Scanner scanner = new Scanner(readable);


    stockView.welcomeMessage();
    while (!this.quit) {
      stockView.printMenu();
      stockView.writeMessage(System.lineSeparator() + "Input instruction: ");
      String userInput = scanner.next();
      processCommand(userInput, scanner);
    }

    stockView.leavingMessage();
  }

  // Processes all available commands.
  private void processCommand(String userInput, Scanner scanner) throws IOException {
    StockFunction function = null;

    String tag;
    LocalDate startDate;
    LocalDate endDate;
    int x;

    switch (userInput) {
      case "1":
      case "stock-price-shift":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.next().toUpperCase();
        if (tag.length() != 4) {
          // Will reset the user interface so they can retype the command.
          System.out.println("Invalid tag");
          processCommand(userInput, scanner);
        }
        stockView.writeMessage("Enter the starting date for your desired time period"
                + System.lineSeparator());
        startDate = getDate(scanner);
        checkCorrectDate(scanner, startDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the ending date for your desired time period"
                + System.lineSeparator());
        endDate = getDate(scanner);
        checkCorrectDate(scanner, endDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);

        // Delegates to the model
        function = model.gainOrLossOverTime(tag, startDate, endDate);
        System.out.println(function.execute());
        break;


      case "2":
      case "xday-moving-average":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.next().toUpperCase();
        if (tag.length() != 4) {
          // Will reset the user interface so they can retype the command.
          System.out.println("Invalid tag");
          processCommand(userInput, scanner);
        }
        stockView.writeMessage("Enter the date to begin the x-day moving average analysis on"
                + System.lineSeparator());
        LocalDate date = getDate(scanner);
        checkCorrectDate(scanner, date.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the number of days the analysis should be performed on"
                + System.lineSeparator());
        x = scanner.nextInt();

        // Delegates to the model
        function = model.movingAverage(tag, date, x);
        System.out.println(function.execute());
        break;


      case "3":
      case "xday-crossovers":
        stockView.writeMessage("Enter stock four digit tag: ");
        tag = scanner.next().toUpperCase();
        if (tag.length() != 4) {
          // Will reset the user interface so they can retype the command.
          System.out.println("Invalid tag");
          processCommand(userInput, scanner);
        }
        stockView.writeMessage("Enter the starting date for your desired time period"
                + System.lineSeparator());
        startDate = getDate(scanner);
        checkCorrectDate(scanner, startDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the ending date for your desired time period"
                + System.lineSeparator());
        endDate = getDate(scanner);
        checkCorrectDate(scanner, endDate.format(DateTimeFormatter.ISO_LOCAL_DATE), userInput);
        stockView.writeMessage("Enter the value of x for the x-day crossover value: ");
        x = scanner.nextInt();

        // Delegates to the model
        function = model.xDayCrossovers(tag, startDate, endDate, x);
        System.out.println(function.execute());
        break;


      case "4":
      case "portfolio":
        // Delegates to the model
        function = model.portfolioOptions(this.readable, this.stockView);
        break;


      case "q":
      case "Q":
      case "quit":
      case "Quit":
        quit = true;
        break;


      default:
        // Will reset the controller to allow the user to correct their command
        stockView.writeMessage("Invalid command: " + userInput + System.lineSeparator());
        control();
        break;
    }

    // Executes the user-given command
    if (function != null) {
      function.execute();
    }
  }

  /**
   * Asks the user to type in a date, and returns that date
   * as a formatted LocalDate Object. Will return an invalid date notice
   * if the date doesn't exist and allow the user to try again.
   *
   * @param scanner Allows the user to input a date.
   * @return The user inputted date as a LocalDate Object.
   */
  protected LocalDate getDate(Scanner scanner) {
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
              + " month=" + month + " year=" + year);
      return getDate(scanner);
    }

    return date;
  }

  // Checks that the inputted date is correct and will reset the user if incorrect
  private void checkCorrectDate(Scanner scanner, String date, String userInput) throws IOException {
    stockView.writeMessage("Is this the correct date? " + date + System.lineSeparator());
    stockView.writeMessage("Y/N: ");
    String response = scanner.next();
    if (response.equalsIgnoreCase("n")) {
      stockView.writeMessage("Restarting...");
      processCommand(userInput, scanner);
    } else if (!response.equalsIgnoreCase("y")) {
      stockView.writeMessage("Invalid input, restarting...");
      processCommand(userInput, scanner);
    }
  }

}
