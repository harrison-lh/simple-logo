package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class PenUpCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public PenUpCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  public double executeCommand(Turtle turtle) {
    turtle.liftPen();
    return turtle.isPenActive() ? 1 : 0;
  }
}
