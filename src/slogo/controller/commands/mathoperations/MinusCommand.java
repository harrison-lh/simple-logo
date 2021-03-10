package slogo.controller.commands.mathoperations;

import slogo.controller.Command;
import slogo.model.Turtle;

public class MinusCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public MinusCommand(){
    numParams = NUM_PARAMS;
  }

  @Override
  public double execute(Turtle turtle) {
    double a = getChildren().get(0).execute(turtle);

    return a * -1;

  }
}
