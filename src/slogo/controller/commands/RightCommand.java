package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * RightCommand is a type of Command that directs the Turtle to rotate right by a specified amount
 * of degrees.
 *
 * @author Harrison Huang
 */
public class RightCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for the RightCommand.
   */
  public RightCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Executes the right command onto the turtle.
   *
   * @param turtle The turtle to be rotated right
   * @return The degrees that the turtle rotated
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double degrees = getChildren().get(0).execute(turtle);
    turtle.right(degrees);
    return degrees;
  }
}
