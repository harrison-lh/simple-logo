package slogo.controller.commands;

import javafx.scene.paint.Paint;
import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * SetPenColor is a type of Command that sets the pen color to the corresponding color index.
 *
 * @author Harrison Huang
 */

public class SetPenColorCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for SetPenColorCommand.
   */
  public SetPenColorCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the pen color to be the color at the given index of the palette.
   *
   * @param turtle The current active turtle
   * @return The index of the color set
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    int index = (int) getChildren().get(0).execute(turtle);
    // TODO: set the pen color to that at the index
    //Paint paint = palette.getColorAtIndex(index);
    //turtle.getPen().setColor(paint);
    return index;
  }

}
