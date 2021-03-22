package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * Power is a type of Command that takes two values and returns the first value to the power of the
 * second value.
 *
 * @author Harrison Huang
 */

public class PowerCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for PowerCommand.
   */
  public PowerCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the first value to the power of the second value.
   *
   * @param turtle           The current turtle
   * @param globalProperties
   * @return the first value to the power of the second value
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double firstVal = getChildren().get(0).execute(turtle, globalProperties);
    double secondVal = getChildren().get(1).execute(turtle, globalProperties);

    return Math.pow(firstVal, secondVal);
  }
}
