package slogo.controller.commands;

import javafx.scene.paint.Color;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

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
   * @param turtle           The current active turtle
   * @param globalProperties The GlobalProperties on which to change the background.
   * @return The index of the color that was set
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    int index = (int) getChildren().get(0).execute(turtle, globalProperties);
    Color color = globalProperties.paletteProperty().get(index);
    globalProperties.setBackgroundColorProperty(color);
    return index;
  }

}
