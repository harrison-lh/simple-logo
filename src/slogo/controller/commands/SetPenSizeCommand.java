package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetPenSize is a type of Command that sets the pen size to a pixel size.
 *
 * @author Harrison Huang
 */

public class SetPenSizeCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for SetPenColorCommand.
   */
  public SetPenSizeCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the pen size to be a certain number of pixels wide.
   *
   * @param turtle The current active turtle
   * @param globalProperties
   * @return The given pixel size
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    int size = (int) getChildren().get(0).execute(turtle, globalProperties);
    globalProperties.setPenSizeProperty(size);
    return size;
  }

}
