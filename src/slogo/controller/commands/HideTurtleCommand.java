package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * HideTurtle is a type of Command that toggles the current visibility of the Turtle to be false.
 *
 * @author Harrison Huang
 */

public class HideTurtleCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for ShowTurtleCommand.
   */
  public HideTurtleCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Toggles the visibility of the turtle to be false.
   *
   * @param turtle           The turtle to be hidden
   * @param globalProperties The global properties
   * @return 0
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    turtle.setVisible(false);
    return 0;
  }

}
