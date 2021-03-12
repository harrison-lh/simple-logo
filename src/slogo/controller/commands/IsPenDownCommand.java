package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantNode;
import slogo.model.Turtle;

/**
 * IsPenDown is a type of Command that returns the current status of the Pen.
 *
 * @author Harrison Huang
 */

public class IsPenDownCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for IsPenDownCommand.
   */
  public IsPenDownCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the current visibility of the Pen.
   *
   * @param turtle The turtle to be checked
   * @return boolean of visibility of Pen
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    return turtle.isPenActive() ? 1 : 0;
  }

}
