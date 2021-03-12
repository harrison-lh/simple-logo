package slogo.controller.commands;

import slogo.controller.ListNodeHead;
import slogo.controller.Node;
import slogo.model.Turtle;

public class ListNodeTail extends Node {

  public ListNodeTail() {
    setIsListEnd(true);
  }

  @Override
  public double execute(Turtle turtle) {
    return 0;
  }
}
