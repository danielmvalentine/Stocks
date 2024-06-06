package model;

import java.util.Date;
import java.util.Scanner;

/**
 * Public class for finding the gain or loss of a stock.
 */
public class StockGainOrLoss {

  private Readable readable;

  public StockGainOrLoss(Readable readable) {
    this.readable = readable;
  }

  public double mainGainOrLoss()throws IllegalArgumentException{
    Scanner scanner = new Scanner(readable);
    System.out.println("Enter stock four digit tag: ");
    String tag = scanner.nextLine();

    // ************************************************************************
    // Finding First Date
    // ************************************************************************

    System.out.println("Enter year for beginning first stock occurrance: ");
    String yearOne = scanner.nextLine();
    System.out.println("Enter month for beginning first stock occurrance: ");
    int intMonth = scanner.nextInt();
    String monthOne = "";
    if(intMonth < 0 && intMonth < 10){
      monthOne = "0" + Integer.toString(intMonth);
    }else{
      monthOne = Integer.toString(intMonth);
    }
    System.out.println("Enter day for beginning first stock occurrance: ");
    int intDay = scanner.nextInt();
    String dayOne = "";
    if(intDay < 0 && intDay < 10){
      dayOne = "0" + Integer.toString(intDay);
    }else{
      dayOne = Integer.toString(intDay);
    }
    String dateOne = "";
    dateOne = dayOne + "/" + monthOne + "/" + yearOne;

    // ************************************************************************
    // In Between
    // ************************************************************************

    System.out.println("Is this the correct first date? " + dateOne);
    System.out.println("Y/N: ");
    if(scanner.nextLine().equals("N") || scanner.nextLine().equals("n")){
      System.out.println("Restarting...");
      mainGainOrLoss();
    }else if(!scanner.nextLine().equals("Y") || !scanner.nextLine().equals("y")){
      throw new IllegalArgumentException("Invalid input, restarting...");
      mainGainOrLoss();
    }

    // ************************************************************************
    // Finding Second Date
    // ************************************************************************

    System.out.println("Enter year for beginning second stock occurrance: ");
    String yearTwo = scanner.nextLine();
    System.out.println("Enter month for beginning second stock occurrance: ");
    intMonth = scanner.nextInt();
    String monthTwo = "";
    if(intMonth < 0 && intMonth < 10){
      monthTwo = "0" + Integer.toString(intMonth);
    }else{
      monthTwo = Integer.toString(intMonth);
    }
    System.out.println("Enter day for beginning stock occurrance: ");
    intDay = scanner.nextInt();
    String dayTwo = "";
    if(intDay < 0 && intDay < 10){
      dayTwo = "0" + Integer.toString(intDay);
    }else{
      dayTwo = Integer.toString(intDay);
    }
    String dateTwo = "";
    dateTwo = dayTwo + "/" + monthTwo + "/" + yearTwo;

    // ************************************************************************
    // In Between
    // ************************************************************************

    System.out.println("Is this the correct second date? " + dateTwo);
    System.out.println("Y/N: ");
    if(scanner.nextLine().equals("N") || scanner.nextLine().equals("n")){
      System.out.println("Restarting...");
      mainGainOrLoss();
    }else if(!scanner.nextLine().equals("Y") || !scanner.nextLine().equals("y")){
      throw new IllegalArgumentException("Invalid input, restarting...");
      mainGainOrLoss();
    }

    // ************************************************************************
    // Final step
    // ************************************************************************

    return findGainOrLoss(tag, dateOne, dateTwo);

  }

  /**
   * Our method that we will use to find the data.
   * @param tag The name of the Stock.
   * @param dateOne The first date of the Stock.
   * @param dateTwo The second date of the Stock.
   * @return Return a double for the total gain or loss.
   */
  public double findGainOrLoss(String tag, String dateOne, String dateTwo){
    // placeholder for now
    return 0;
  }
}
