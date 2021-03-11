package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class RepeatCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for the RepeatCommand.
   */
  public RepeatCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double lastVal = 0.0;
    for(int i = 0; i < getChildren().get(0).execute(turtle); i++) {
      lastVal = getChildren().get(1).execute(turtle);
    }
    return lastVal;
  }
}
