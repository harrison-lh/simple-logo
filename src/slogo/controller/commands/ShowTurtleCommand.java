package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * ShowTurtle is a type of Command that toggles the current visibility of the Turtle to be true.
 *
 * @author Harrison Huang
 */

public class ShowTurtleCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for ShowTurtleCommand.
   */
  public ShowTurtleCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Toggles the visibility of the turtle to be true.
   *
   * @param turtle           The turtle to be shown
   * @param globalProperties The global properties
   * @return 1
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    turtle.setVisible(true);
    return 1;
  }

}
