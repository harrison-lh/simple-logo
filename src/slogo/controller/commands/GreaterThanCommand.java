package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantNode;
import slogo.model.Turtle;

/**
 * GreaterThan is a type of Command that compares two values and checks whether the first value is
 * strictly greater than the second value.
 *
 * @author Harrison Huang
 */

public class GreaterThanCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for GreaterThanCommand.
   */
  public GreaterThanCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Compares two values to see if the first is greater than the second.
   *
   * @param turtle The current turtle
   * @return 1 if first is greater than second, else 0
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double firstVal = getChildren().get(0).execute(turtle);
    double secondVal = getChildren().get(1).execute(turtle);

    return (firstVal > secondVal) ? 1 : 0;
  }
}
