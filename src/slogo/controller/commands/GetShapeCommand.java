package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * GetPenColor is a type of Command that gets the current turtle shape.
 *
 * @author Harrison Huang
 */

public class GetShapeCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for GetShapeCommand.
   */
  public GetShapeCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Gets the current turtle shape.
   *
   * @param turtle The current active turtle
   * @param globalProperties
   * @return The index of the current turtle shape
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    // TODO: get the current shape of the turtle
    //return turtle.getShape();
    return 0;
  }

}
