package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * GetPenColor is a type of Command that gets the current pen color.
 *
 * @author Harrison Huang
 */

public class GetPenColorCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for GetPenColorCommand.
   */
  public GetPenColorCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Gets the current pen color.
   *
   * @param turtle The current active turtle
   * @param globalProperties
   * @return The index of the current pen color
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    // TODO: get the current pen color of the turtle
    //return turtle.getPen().getColor();
    return 0;
  }

}
