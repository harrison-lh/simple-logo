package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * Not is a type of Command that returns 1 if the value is 0, and 0 if the value is nonzero.
 *
 * @author Harrison Huang
 */

public class NotCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for NotCommand.
   */
  public NotCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the opposite boolean of the input value.
   *
   * @param turtle The current turtle
   * @param globalProperties
   * @return 1 if the value is 0, and 0 if the value is nonzero
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double val = getChildren().get(0).execute(turtle, globalProperties);
    return (val == 0) ? 1 : 0;
  }
}
