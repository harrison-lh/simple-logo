package slogo.controller;

import slogo.model.Turtle;

public class ListCommandTail extends Command {

  public ListCommandTail() {
    setIsListEnd(true);
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    return 0;
  }
}
