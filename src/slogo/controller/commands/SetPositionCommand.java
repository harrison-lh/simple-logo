package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetXY is a type of Command that places the Turtle at the specified coordinates.
 *
 * @author Harrison Huang
 */

public class SetPositionCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for SetPositionCommand.
   */
  public SetPositionCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the position of the turtle to be at the specified coordinates.
   *
   * @param turtle           The turtle to be set
   * @param globalProperties The global properties
   * @return The distance the turtle moved
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double newX = getChildren().get(0).execute(turtle, globalProperties);
    double newY = getChildren().get(1).execute(turtle, globalProperties);
    double distance = getDistance(turtle.getX(), turtle.getY(), newX, newY);

    turtle.setPosition(newX, newY);

    return distance;
  }

  private double getDistance(double currX, double currY, double newX, double newY) {
    double delX = newX - currX;
    double delY = newY - currY;

    return Math.sqrt(Math.pow(delX, 2) + Math.pow(delY, 2));
  }


}
