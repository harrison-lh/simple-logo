package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * LessThan is a type of Command that compares two values and checks whether the first value is strictly
 * less than the second value.
 *
 * @author Harrison Huang
 */

public class LessThanCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for LessThanCommand.
   */
  public LessThanCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Compares two values to see if the first is less than the second.
   *
   * @param turtle The current turtle
   * @return 1 if first is less than second, else 0
   */
  @Override
  public double executeCommand(Turtle turtle) {
    double firstVal = getChildren().get(0).execute(turtle);
    double secondVal = getChildren().get(1).execute(turtle);

    return (firstVal < secondVal) ? 1 : 0;
  }
}
