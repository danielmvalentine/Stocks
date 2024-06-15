package model.stockfunctions;

import java.io.IOException;

/**
 * Our Interface that our public methods for functions call. We will use these for all
 * classes in our functions package.
 */
public interface StockFunction {
  /**
   * Executes the given command.
   *
   * @return The result in String form.
   */
  String execute() throws IOException;
}
