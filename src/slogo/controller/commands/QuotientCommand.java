package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class QuotientCommand extends Command {

  private static final int NUM_PARAMS = 2;

  public QuotientCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  public double execute(Turtle turtle) {
    double a = getChildren().get(0).execute(turtle);
    double b = getChildren().get(1).execute(turtle);

    return a / b;

  }
}