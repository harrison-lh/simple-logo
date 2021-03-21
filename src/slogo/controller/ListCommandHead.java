package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;

public class ListCommandHead extends Command {

  public ListCommandHead(){
    innerChildren = new ArrayList<>();
  }

  protected List<Command> innerChildren;





  public List<Command> getInnerChildren(){
    return innerChildren;
  }

  public void addInnerChild(Command innerChild){
    this.innerChildren.add(innerChild);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double lastVal = 0;
    for(Command child : innerChildren){
      lastVal = child.execute(turtle, globalProperties);
    }
    return lastVal;


  }

  @Override
  public String toString(){
    return "ListCommandHead";
  }
}
