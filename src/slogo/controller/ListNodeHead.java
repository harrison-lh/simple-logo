package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;

public class ListNodeHead extends Node {
  private List<Node> innerChildren;

  public ListNodeHead(){
    innerChildren = new ArrayList<>();

  }

  public List<Node> getInnerChildren(){
    return innerChildren;
  }

  public void addInnerChild(Node innerChild){
    this.innerChildren.add(innerChild);
  }

  @Override
  public double execute(Turtle turtle) {
    double lastVal = 0;
    for(Node child : innerChildren){
      lastVal = child.execute(turtle);
    }
    return lastVal;

  }

  @Override
  public String toString(){
    return "ListNodeHead";
  }
}
