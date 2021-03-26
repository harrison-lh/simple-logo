package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * NaturalLog is a type of Command that returns the natural log of the value.
 *
 * @author Harrison Huang
 */

public class NaturalLogCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for NaturalLogCommand.
   */
  public NaturalLogCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the natural log of the input value.
   *
   * @param turtle           The current turtle
   * @param globalProperties The global properties
   * @return double of natural log of value
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double val = getChildren().get(0).execute(turtle, globalProperties);
    return Math.log(val);
  }
}
