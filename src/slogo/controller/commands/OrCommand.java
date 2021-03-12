package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantNode;
import slogo.model.Turtle;

/**
 * Or is a type of Command that checks whether at least one value is nonzero.
 *
 * @author Harrison Huang
 */

public class OrCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for OrCommand.
   */
  public OrCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Checks whether at least one value is nonzero.
   *
   * @param turtle The current turtle
   * @return 1 if one value is nonzero, else 0
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double firstVal = getChildren().get(0).execute(turtle);
    double secondVal = getChildren().get(1).execute(turtle);

    return ((firstVal != 0) || (secondVal != 0)) ? 1 : 0;
  }
}
