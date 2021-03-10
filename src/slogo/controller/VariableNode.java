package slogo.controller;

import slogo.model.Turtle;

/**
 * VariableNode is a subclass of Node the implements the behavior of variables within SLogo code.
 * VariableNode has a name and a value, like normal variables, and getters and setters for each.
 * Upon calling execute, VariableNode simply returns its current value.
 *
 * @author Marc Chmielewski
 */
public class VariableNode extends Node {
  private String name;
  private double value;

  /**
   * Common case constructor for VariableNode. Takes in a String name, and a double value and maps
   * them to each other.
   *
   * @param name The name of the variable.
   * @param value The value of the variable.
   */
  public VariableNode(String name, double value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Less-common case constructor for VariableNode. Takes in a String name, and initializes the
   * value to zero.
   *
   * @param name The name of the variable
   */
  public VariableNode(String name) {
    this.name = name;
    this.value = 0;
  }

  /**
   * Execution behavior of the VariableNode. Simply returns its value.
   *
   * @param turtle The turtle to which this VariableNode belongs
   * @return The value of the VariableNode.
   */
  @Override
  public double execute(Turtle turtle) {
    return value;
  }

  /**
   * Gets the name of the VariableNode.
   * @return The name of the VariableNode
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the VariableNode
   *
   * @param name The new name of the VariableNode
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the value of the VariableNode
   *
   * @return The value of the VariableNode
   */
  public double getValue() {
    return value;
  }

  /**
   * Sets the value of this VariableNode.
   *
   * @param value The value of this VariableNode.
   */
  public void setValue(double value) {
    this.value = value;
  }
}
