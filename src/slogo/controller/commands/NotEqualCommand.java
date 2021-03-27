package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the NotEqual command into the command line, and compares the two values given.
 * It assumes that the user provides two "children", or subsequent commands -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command equalCommand = new NotEqualCommand();
 * Command notEqualCommand = new NotEqualCommand();
 *
 * double firstVal = 50;
 * Command firstConstant = new ConstantCommand(firstVal);
 * equalCommand.addChild(firstConstant);
 * notEqualCommand.addChild(firstConstant);
 * double secondVal = 50;
 * Command secondConstant = new ConstantCommand(secondVal);
 * equalCommand.addChild(secondConstant);
 * double thirdVal = 45;
 * Command thirdConstant = new ConstantCommand(thirdVal);
 * notEqualCommand.addChild(thirdConstant);
 *
 * double isEqual = equalCommand.execute(turtle, globalParams); // this will be 0 in this example
 * double notEqual = notEqual.execute(turtle, globalParams); // this will be 1 in this example
 * ...
 *
 * A thing to note when using NotEqualCommand.java is that when it is time to compare, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
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
