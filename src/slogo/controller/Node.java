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

  private int numParams;
  private List<Node> children = new ArrayList<>();

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

  /**
   * Sets the number of parameters that the Node takes.
   *
   * @param numParams The number of parameters the Node takes
   */
  protected void setNumParams(int numParams) {
    this.numParams = numParams;
  }

  /**
   * Returns the number of parameters that this Command takes
   *
   * @return The number of parameters that this Command takes
   */
  public int getNumParams() {
    return numParams;
  }

}
