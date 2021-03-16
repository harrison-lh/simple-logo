package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * SetBackground is a type of Command that sets the background color to the corresponding color
 * index.
 *
 * @author Harrison Huang
 */

public class SetBackgroundCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for SetBackgroundCommand.
   */
  public SetBackgroundCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the background to be the color at the given index of the palette.
   *
   * @param turtle The current active turtle
   * @return The index of the color set
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    int index = (int) getChildren().get(0).execute(turtle);
    //TODO: set the background to that at the index
    //Paint paint = palette.getColorAtIndex(index);
    //background.setColor(paint);
    return index;
  }

}
