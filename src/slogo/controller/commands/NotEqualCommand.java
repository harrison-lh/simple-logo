package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * NotEqual is a type of Command that compares two values and checks whether the first value is not
 * equal to the second value.
 *
 * @author Harrison Huang
 */

public class NotEqualCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for NotEqualCommand.
   */
  public NotEqualCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Compares two values to see if the first is not equal to the second.
   *
   * @param turtle           The current turtle
   * @param globalProperties The global properties
   * @return 1 if first is not equal to second, else 0
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double firstVal = getChildren().get(0).execute(turtle, globalProperties);
    double secondVal = getChildren().get(1).execute(turtle, globalProperties);

    return (firstVal != secondVal) ? 1 : 0;
  }
}
