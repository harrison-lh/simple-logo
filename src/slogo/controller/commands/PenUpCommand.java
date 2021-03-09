package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class PenUpCommand extends Command {

  @Override
  public double execute(Turtle turtle) {
    return turtle.isPenActive() ? 1 : 0;
  }
}
