package slogo.controller.commands;

import javafx.scene.paint.Color;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetPalette is a type of Command that sets the index of the palette to have the given RBG color.
 *
 * @author Harrison Huang
 */

public class SetPaletteCommand extends Command {

  private static final int NUM_PARAMS = 4;

  /**
   * Constructor for SetPaletteCommand.
   */
  public SetPaletteCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the index of the palette to have the given RBG color.
   *
   * @param turtle The current active turtle
   * @param globalProperties
   * @return The index that was set
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    int index = (int) getChildren().get(0).execute(turtle, globalProperties);
    int red = (int) getChildren().get(1).execute(turtle, globalProperties);
    int green = (int) getChildren().get(2).execute(turtle, globalProperties);
    int blue = (int) getChildren().get(3).execute(turtle, globalProperties);
    globalProperties.paletteProperty().set(index, Color.rgb(red, green, blue));
    return index;
  }

}
