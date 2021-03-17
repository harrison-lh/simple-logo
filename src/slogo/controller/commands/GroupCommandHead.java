package slogo.controller.commands;

import java.util.ArrayList;
import slogo.controller.Command;
import slogo.model.Turtle;

public class GroupCommandHead extends CollectionCommandHead {

  public GroupCommandHead(){
    innerChildren = new ArrayList<>();
  }

  @Override
  protected double executeCommand(Turtle turtle) {
    return 0;
  }
}
