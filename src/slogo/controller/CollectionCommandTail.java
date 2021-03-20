package slogo.controller;

import slogo.model.Turtle;

public class CollectionCommandTail extends Command {

  public CollectionCommandTail() {
    setIsCollectionEnd(true);
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    return 0;
  }


  @Override
  public String toString(){
    return "CollectionCommandTail";
  }
}
