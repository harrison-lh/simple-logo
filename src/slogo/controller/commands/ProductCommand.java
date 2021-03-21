package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class ProductCommand extends Command {

  private static final int NUM_PARAMS = 2;

  public ProductCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double a = getChildren().get(0).execute(turtle, globalProperties);
    double b = getChildren().get(1).execute(turtle, globalProperties);

    return a * b;

  }
}
