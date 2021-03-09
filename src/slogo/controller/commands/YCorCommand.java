package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class YCorCommand extends Command {

  @Override
  public double execute(Turtle turtle) {
    return turtle.getY();
  }
}
