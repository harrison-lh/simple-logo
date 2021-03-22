package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class PenUpCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public PenUpCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    turtle.liftPen();
    return turtle.isPenActive() ? 1 : 0;
  }
}
