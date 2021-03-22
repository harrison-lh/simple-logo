package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * ID is a type of Command that gets the current turtle ID.
 *
 * @author Harrison Huang
 */

public class IDCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for IDCommand.
   */
  public IDCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Gets the current turtle ID.
   *
   * @param turtle           The current active turtle
   * @param globalProperties
   * @return The current turtle ID
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    // TODO: get the current ID of the turtle
    return turtle.getId();
  }

}
