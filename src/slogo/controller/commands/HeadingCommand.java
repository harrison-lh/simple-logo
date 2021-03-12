package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class HeadingCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public HeadingCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    return turtle.getHeading();
  }
}
