package slogo.controller;

import slogo.model.Turtle;

public class ListNodeTail extends Command {

  public ListNodeTail() {
    setIsListEnd(true);
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    return 0;
  }
}
