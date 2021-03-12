package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class ArcTangentCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public ArcTangentCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    double degrees = getChildren().get(0).execute(turtle);

    return Math.toDegrees(Math.atan(Math.toRadians(degrees)));
  }
}

