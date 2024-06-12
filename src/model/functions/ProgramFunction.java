package model.functions;

import java.io.IOException;

/**
 * Our Interface that our public methods for functions call. We will use these for all
 * classes in our functions package.
 */
public interface ProgramFunction {
  /**
   * Executes the given command.
   *
   * @return The result in String form.
   */
  String execute() throws IOException;
}
