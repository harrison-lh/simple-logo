package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;

public class ListNodeHead extends Node {
  private List<Node> children;

  public ListNodeHead(){
    children = new ArrayList<>();

  }

  public void addInnerChild(Node innerChild){
    this.children.add(innerChild);
  }

  @Override
  public double execute(Turtle turtle) {
    double lastVal = 0;
    for(Node child : children){
      lastVal = child.execute(turtle);
    }
    return lastVal;

  }

  @Override
  public String toString(){
    return "ListNodeHead";
  }
}
