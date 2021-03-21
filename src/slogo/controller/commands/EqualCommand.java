package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * Equal is a type of Command that compares two values and checks whether the first value is equal
 * to the second value.
 *
 * @author Harrison Huang
 */

public class EqualCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for EqualCommand.
   */
  public EqualCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Compares two values to see if the first is equal to the second.
   *
   * @param turtle The current turtle
   * @param globalProperties
   * @return 1 if first is equal to second, else 0
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double firstVal = getChildren().get(0).execute(turtle, globalProperties);
    double secondVal = getChildren().get(1).execute(turtle, globalProperties);

    return (firstVal == secondVal) ? 1 : 0;
  }
}
