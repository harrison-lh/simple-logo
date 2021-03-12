package slogo.controller;

import slogo.model.Turtle;

public class ConstantNode extends Node {
  private double value;

  /**
   * Common-constructor for a ConstantNode. Simply assigns the ConstantNode a value.
   * @param value The value of the ConstantNode.
   */
  public ConstantNode(double value) {
    this.value = value;
  }

  /**
   * Gets the value of the ConstantNode
   *
   * @return The value of the VariableNode
   */
  public double getValue() {
    return value;
  }

  /**
   * Sets the value of this ConstantNode.
   *
   * @param value The value of this VariableNode.
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Returns the value of this ConstantNode.
   *
   * @param turtle The turtle that owns this ConstantNode.
   * @return The value of this ConstantNode.
   */
  @Override
  public double execute(Turtle turtle) {
    return value;
  }

  @Override
  public String toString(){
    return ("Constant of Value: " + Double.toString(value));
  }
}
