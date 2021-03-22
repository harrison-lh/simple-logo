package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * IsShowing is a type of Command that returns the current visibility of the Turtle.
 *
 * @author Harrison Huang
 */

public class IsShowingCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for IsShowingCommand.
   */
  public IsShowingCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the current visibility of the turtle.
   *
   * @param turtle           The turtle to be checked
   * @param globalProperties
   * @return boolean of visibility of turtle
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    return turtle.isVisible() ? 1 : 0;
  }

}
