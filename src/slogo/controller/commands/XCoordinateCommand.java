package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class XCoordinateCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public XCoordinateCommand(){
    numParams = NUM_PARAMS;
  }

  @Override
  public double execute(Turtle turtle) {
    return turtle.getX();
  }
}
