package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class PenDownCommand extends Command {

  @Override
  public double execute(Turtle turtle) {
    return turtle.isPenActive() ? 0 : 1;
  }
}
