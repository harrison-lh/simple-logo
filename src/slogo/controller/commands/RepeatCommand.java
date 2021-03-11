package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * RepeatCommand implements the ability to repeat an list of Commands a number of times
 *
 * @author Marc Chmielewski
 */
public class RepeatCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for the RepeatCommand.
   */
  public RepeatCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Implementation of the RepeatCommand execute method. Forces the children of the listNode to
   * repeat as many times as the value of parameter 0. Returns the value of the last operation.
   *
   * @param turtle The turtle on which the Commands are acting
   * @return Returns the return value of the last operation.
   */
  @Override
  public double execute(Turtle turtle) {
    assert (getChildren().size() == getNumParams());

    double lastVal = 0.0;
    for (int i = 0; i < getChildren().get(0).execute(turtle); i++) {
      lastVal = getChildren().get(1).execute(turtle);
    }
    return lastVal;
  }
}
