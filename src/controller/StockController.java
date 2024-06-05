package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * Our basic controller that we use to represent
 */
public class StockController {

  private Readable readable;
  private StringBuilder appendable = new StringBuilder();

  public StockController() {
    StringBuilder appendable = new StringBuilder();
  }

  private void writeMessage(String message) throws IllegalStateException {
    try{
      appendable.append(message);
    }catch(IllegalStateException e){
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Our method that writes the string that will be printed on the screen for our menu.
   */
  private void printMenu() {
    writeMessage("Supported user instructions are: " + System.lineSeparator());
    writeMessage("1 - Examine the gain or loss of a stock over a specified period."
            + System.lineSeparator());
    writeMessage("2 - Examine the x-day moving average of a stock for a specified date and" +
            " a specified value of x."
            + System.lineSeparator());
    writeMessage("3 - Determine which days are x-day crossovers for a specific stock over " +
            "a specific date range and a specific value of x." + System.lineSeparator());
    writeMessage("4 - Create or view a portfolio." + System.lineSeparator());
    writeMessage("Q - Quit the program." + System.lineSeparator());
  }

  /**
   * Our main method for our controller to edit maneuvering around our menu.
   */
  public void mainMenuMethod(){
    Scanner scanner = new Scanner(readable);
    boolean quit = false;

    this.welcomeMessage();
    while(!quit){
      writeMessage("Input instruction: ");
      String userInput = scanner.nextLine();
      switch(userInput){

      }
    }



  }

  /**
   * Simply prints our welcome message at the beginning of our application.
   */
  private void welcomeMessage(){
    writeMessage("Welcome to the Stocks Program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * Method for leaving the program.
   */
  private void leavingMessage(){
    writeMessage("Thank you for using this program!");
  }

}
