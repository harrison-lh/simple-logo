package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetShape is a type of Command that sets the turtle shape to the corresponding shape index.
 *
 * @author Harrison Huang
 * @author Marc Chmielewski
 */

public class SetShapeCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for SetShapeCommand.
   */
  public SetShapeCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the turtle to have the shape at the given index.
   *
   * @param turtle           The turtle to be set
   * @param globalProperties The GlobalProperties onto which to set the shape
   * @return The index of the turtle shape
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    int index = (int) getChildren().get(0).execute(turtle, globalProperties);
    globalProperties.setTurtleShapeProperty(globalProperties.getShapeMap().get(index));
    return index;
  }

}
