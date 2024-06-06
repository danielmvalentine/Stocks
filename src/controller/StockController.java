package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import view.StockView;

/**
 * Our basic controller that we use to represent
 */
public class StockController {

  private Readable readable;
  StockView stockView = new StockView();



  /**
   * Our main method for our controller to edit maneuvering around our menu.
   */
  public void mainMenuMethod(){
    Scanner scanner = new Scanner(readable);
    boolean quit = false;

    stockView.welcomeMessage();
    while(!quit){
      stockView.writeMessage("Input instruction: ");
      String userInput = scanner.nextLine();
      switch(userInput){
        case "1":
          System.out.println("Enter stock four digit tag: ");
          String tag = scanner.nextLine();
          System.out.println("Enter how many days back you would like to start checking stock " +
                  "data from: ");
          String daysBack = scanner.nextLine();

        case "Q":
        case "q":
        case "quit":
        case "Quit":
          quit = true;
          break;
      }
    }
  }

}
