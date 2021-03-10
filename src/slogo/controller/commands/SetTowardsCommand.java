package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * Towards is a type of Command that directs the Turtle to point toward a certain point on screen.
 *
 * @author Harrison Huang
 */

public class SetTowardsCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final double HALF_CIRCLE = 180;
  private static final double FULL_CIRCLE = 360;

  /**
   * Constructor for SetHeadingCommand.
   */
  public SetTowardsCommand() {
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

    double toXPos = getChildren().get(0).execute(turtle);
    double toYPos = getChildren().get(1).execute(turtle);

    double newHeading = getNewHeading(turtle.getX(), turtle.getY(), toXPos, toYPos);
    double oldHeading = turtle.getHeading();

    turtle.setHeading(newHeading);

    return Math.abs(newHeading - oldHeading);
  }

  private double getNewHeading(double currX, double currY, double newX, double newY) {
    double delX = newX - currX;
    double delY = newY - currY;

    double newHeading = Math.atan(delY / delX);
    if (delX < 0) newHeading += HALF_CIRCLE;

    if (newHeading < 0) newHeading += FULL_CIRCLE;
    return newHeading;
  }


}
