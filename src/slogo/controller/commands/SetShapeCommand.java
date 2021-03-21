package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * SetShape is a type of Command that sets the turtle shape to the corresponding shape index.
 *
 * @author Harrison Huang
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
   * @param turtle The turtle to be set
   * @param globalProperties
   * @return The index of the turtle shape
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    int index = (int) getChildren().get(0).execute(turtle, globalProperties);
    //TODO: set the shape to that at the index
    //turtle.setShapeAtIndex(index);
    return index;
  }

}
