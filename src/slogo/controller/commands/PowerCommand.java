package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Power command into the command line, and raises the power the values given.
 * It assumes that the user provides two "children", or subsequent commands -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties, and Java.Math.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command powerCommand = new PowerCommand();
 *
 * double firstVal = 2;
 * Command firstConstant = new ConstantCommand(firstVal);
 * powerCommand.addChild(firstConstant);
 * double secondVal = 3;
 * Command secondConstant = new ConstantCommand(secondVal);
 * powerCommand.addChild(secondConstant);
 *
 * double power = powerCommand.execute(turtle, globalParams); // this will be 8 in this example
 * ...
 *
 * A thing to note when using PowerCommand.java is that when it is time to calculate the difference, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 * @author Harrison Huang
 */

public class PowerCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for PowerCommand.
   */
  public PowerCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the first value to the power of the second value.
   *
   * @param turtle           The current turtle
   * @param globalProperties The global properties
   * @return the first value to the power of the second value
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double firstVal = getChildren().get(0).execute(turtle, globalProperties);
    double secondVal = getChildren().get(1).execute(turtle, globalProperties);

    return Math.pow(firstVal, secondVal);
  }
}
