package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Not command into the command line, checks to see if the value given is 0.
 * It assumes that the user provides one "child", or subsequent command -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command notCommand = new NotCommand();
 * Command yesCommand = new NotCommand();
 *
 * double val = 0;
 * Command constant = new ConstantCommand(val);
 * notCommand.addChild(constant);
 *
 * double zeroVal = 50;
 * Command zeroConstant = new ConstantCommand(zeroVal);
 * yesCommand.addChild(zeroConstant);
 *
 * double notVal = notCommand.execute(turtle, globalParams); // this will be 1 in this example
 * double yesVal = yesCommand.execute(turtle, globalParams); // this will be 0 in this example
 * ...
 *
 * A thing to note when using MinusCommand.java is that when it is time to calculate the negative, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */

public class NotCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for NotCommand.
   */
  public NotCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the opposite boolean of the input value.
   *
   * @param turtle           The current turtle
   * @param globalProperties The global properties
   * @return 1 if the value is 0, and 0 if the value is nonzero
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double val = getChildren().get(0).execute(turtle, globalProperties);
    return (val == 0) ? 1 : 0;
  }
}
