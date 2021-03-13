package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * ClearScreen is a type of Command that places the Turtle at (0,0) and removes all lines.
 *
 * @author Harrison Huang
 */

public class ClearScreenCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for ClearScreenCommand.
   */
  public ClearScreenCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the position of the turtle to be at home (0,0) and removes all lines.
   *
   * @param turtle The turtle to be set
   * @return The distance the turtle moved
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    Command goHome = new HomeCommand();
    double distance = goHome.execute(turtle);
    turtle.clearScreen();
    return distance;
  }

}
