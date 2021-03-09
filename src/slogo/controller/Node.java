package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;

/**
 * Node represents the most basic unit of a parse-tree/AST. It is extended by several other classes,
 * most notably Command, and allows the program to leverage polymorphism to construct trees in an
 * efficient manner.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public abstract class Node {
  List<Node> children = new ArrayList<>();

  /**
   * Add an additional child Node to the Node.
   *
   * @param child The Node to add to the children
   */
  public void addChild(Node child) {
    children.add(child);
  }

  /**
   * Simple getter to return the list of child Nodes
   *
   * @return A List of child Nodes
   */
  public List<Node> getChildren() {
    return children;
  }

  /**
   * The execution behavior of the Node. That is, what it does once the AST is being consumed.
   *
   * @return The double for the return value of each execute
   */
  public abstract double execute(Turtle turtle);

}
