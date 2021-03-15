package slogo.controller.commands;

import javafx.scene.paint.Paint;
import slogo.controller.Command;
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
   * @return The index of the current pen color
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    // TODO: get the current pen color of the turtle
    //return turtle.getPen().getColor();
    return 0;
  }

}
