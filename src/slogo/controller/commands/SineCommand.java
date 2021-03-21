package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class SineCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public SineCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double degrees = getChildren().get(0).execute(turtle, globalProperties);

    return Math.toDegrees(Math.sin(Math.toRadians(degrees)));
  }
}

