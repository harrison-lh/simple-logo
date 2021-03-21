package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class YCoordinateCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public YCoordinateCommand(){
    setNumParams(NUM_PARAMS);
  }


  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    return turtle.getY();
  }
}
