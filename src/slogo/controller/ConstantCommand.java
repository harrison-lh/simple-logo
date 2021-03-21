package slogo.controller;

import slogo.model.Turtle;

public class ConstantCommand extends Command {
  private double value;

  /**
   * Common-constructor for a ConstantCommand. Simply assigns the ConstantCommand a value.
   * @param value The value of the ConstantCommand.
   */
  public ConstantCommand(double value) {
    this.value = value;
  }

  /**
   * Gets the value of the ConstantCommand
   *
   * @return The value of the VariableCommand
   */
  public double getValue() {
    return value;
  }

  /**
   * Sets the value of this ConstantCommand.
   *
   * @param value The value of this VariableCommand.
   */
  public void setValue(double value) {
    this.value = value;
  }

  /**
   * Returns the value of this ConstantCommand.
   *
   * @param turtle The turtle that owns this ConstantCommand.
   * @param globalProperties
   * @return The value of this ConstantCommand.
   */
  @Override
  public double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    return value;
  }
}
