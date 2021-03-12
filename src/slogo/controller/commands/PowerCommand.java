package slogo.controller.commands;

import slogo.controller.Command;
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
   * @param turtle The current turtle
   * @return the first value to the power of the second value
   */
  @Override
  public double executeCommand(Turtle turtle) {
    double firstVal = getChildren().get(0).execute(turtle);
    double secondVal = getChildren().get(1).execute(turtle);

    return Math.pow(firstVal, secondVal);
  }
}
