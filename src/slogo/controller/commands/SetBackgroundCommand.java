package slogo.controller.commands;

import javafx.scene.paint.Color;
import slogo.controller.Command;
import slogo.controller.TurtleGeneral;
import slogo.model.Turtle;
import slogo.view.MainView;

/**
 * SetBackground is a type of Command that sets the background color to the corresponding color
 * index.
 *
 * @author Harrison Huang
 * @author Marc Chmielewski
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
    // Color color = TurtleGeneral.palette.getColorAtIndex(index);
    // MainView.setBackgroundColor(color);
    return index;
  }

}
