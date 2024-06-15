package controller;

import java.io.IOException;

/**
 * Represents a generic controller.
 */
public interface Controller {

  /**
   * The method to control the user inputs of the program.
   *
   * @throws IOException In certain cases from the functions of the program.
   */
  void control() throws IOException;
}
