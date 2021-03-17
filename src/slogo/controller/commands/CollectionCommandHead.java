package slogo.controller.commands;

import java.util.ArrayList;
import java.util.List;
import slogo.controller.Command;
import slogo.model.Turtle;

public abstract class CollectionCommandHead extends Command {

  protected List<Command> innerChildren;


  public List<Command> getInnerChildren(){
    return innerChildren;
  }

  public void addInnerChild(Command innerChild){
    this.innerChildren.add(innerChild);
  }

}
