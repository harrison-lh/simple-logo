package slogo.controller;

import slogo.model.Turtle;

/**
 * VariableCommand is a subclass of Command the implements the behavior of variables within SLogo code.
 * VariableCommand has a name and a value, like normal variables, and getters and setters for each.
 * Upon calling execute, VariableCommand simply returns its current value.
 *
 * @author Marc Chmielewski
 */
public class VariableCommand extends ConstantCommand {

  private String name;

  /**
   * Common case constructor for VariableCommand. Takes in a String name, and a double value and maps
   * them to each other.
   *
   * @param name  The name of the variable.
   * @param value The value of the variable.
   */
  public VariableCommand(String name, double value) {
    super(value);
    this.name = name;
  }

  /**
   * Less-common case constructor for VariableCommand. Takes in a String name, and initializes the
   * value to zero.
   *
   * @param name The name of the variable
   */
  public VariableCommand(String name) {
    super(0);
    this.name = name;
  }

  /**
   * Execution behavior of the VariableCommand. Simply returns its value.
   *
   * @param turtle The turtle to which this VariableCommand belongs
   * @param globalProperties
   * @return The value of the VariableCommand.
   */
  @Override
  public double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    return turtle.getVars().getValue(name);
  }

  /**
   * Gets the name of the VariableCommand.
   *
   * @return The name of the VariableCommand
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the VariableCommand
   *
   * @param name The new name of the VariableCommand
   */
  public void setName(String name) {
    this.name = name;
  }
}
