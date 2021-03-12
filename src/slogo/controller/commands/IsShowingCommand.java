package slogo.controller.commands;

import slogo.controller.Command;
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
   * @param turtle The turtle to be checked
   * @return boolean of visibility of turtle
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    return turtle.isVisible() ? 1 : 0;
  }

}
