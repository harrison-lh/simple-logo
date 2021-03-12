package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class PenDownCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public PenDownCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    turtle.placePen();
    return turtle.isPenActive() ? 0 : 1;
  }
}
