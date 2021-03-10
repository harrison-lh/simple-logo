package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantNode;
import slogo.model.Turtle;

/**
 * NaturalLog is a type of Command that returns the natural log of the value.
 *
 * @author Harrison Huang
 */

public class NaturalLogCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for NaturalLogCommand.
   */
  public NaturalLogCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the natural log of the input value.
   *
   * @param turtle The current turtle
   * @return double of natural log of value
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double val = getChildren().get(0).execute(turtle);

    return Math.log(val);
  }
}
