package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * Turtles is a type of Command that gets the current number of turtles.
 *
 * @author Harrison Huang
 */

public class TurtlesCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for TurtlesCommand.
   */
  public TurtlesCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Gets the current number of turtles.
   *
   * @param turtle           The current active turtle
   * @param globalProperties
   * @return The current number of turtles
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    // TODO: get the current number of turtles
    //return turtle.getTotalNum();
    return globalProperties.getNumTurtlesCreated();
  }

}
