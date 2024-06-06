package controller;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import view.StockView;
import model.StockGainOrLoss;

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
          // This will be the way to call the method.
          // double finalValue = mainGainOrLoss();
          System.out.println();

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
