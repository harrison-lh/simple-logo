package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * LeftCommand is a type of Command that directs the Turtle to rotate left by a specified amount
 * of degrees.
 *
 * @author Harrison Huang
 */
public class LeftCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for the LeftCommand.
   */
  public LeftCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Executes the left command onto the turtle.
   *
   * @param turtle The turtle to be rotated left
   * @return The degrees that the turtle rotated
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double degrees = getChildren().get(0).execute(turtle);
    turtle.right(-1 * degrees);
    return degrees;
  }
}
