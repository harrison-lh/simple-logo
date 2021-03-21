package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetHeading is a type of Command that directs the Turtle to point to a certain direction
 * specified in degrees.
 *
 * @author Harrison Huang
 */

public class SetHeadingCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for SetHeadingCommand.
   */
  public SetHeadingCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the heading of the turtle to point in a certain direction.
   *
   * @param turtle The turtle to be set
   * @param globalProperties
   * @return The direction the turtle is now pointing
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double degrees = getChildren().get(0).execute(turtle, globalProperties);
    turtle.setHeading(degrees);
    return degrees;
  }
}
