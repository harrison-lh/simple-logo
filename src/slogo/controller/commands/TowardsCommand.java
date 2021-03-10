package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * Towards is a type of Command that directs the Turtle to point toward a certain point on screen.
 *
 * @author Harrison Huang
 */

public class TowardsCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for SetHeadingCommand.
   */
  public TowardsCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the heading of the turtle to point toward the specified coordinates.
   *
   * @param turtle The turtle to be set
   * @return The number of degrees the turtle turned
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double xPos = turtle.getX();
    double yPos = turtle.getY();

    double toXPos = getChildren().get(0).execute(turtle);
    double toYPos = getChildren().get(1).execute(turtle);

    double degrees = getChildren().get(0).execute(turtle);
    turtle.setHeading(degrees);
    return degrees;
  }
}
