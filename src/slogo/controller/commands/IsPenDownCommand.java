package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * IsPenDown is a type of Command that returns the current status of the Pen.
 *
 * @author Harrison Huang
 */

public class IsPenDownCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for IsPenDownCommand.
   */
  public IsPenDownCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the current visibility of the Pen.
   *
   * @param turtle           The turtle to be checked
   * @param globalProperties The global properties
   * @return boolean of visibility of Pen
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    return turtle.isPenActive() ? 1 : 0;
  }

}
