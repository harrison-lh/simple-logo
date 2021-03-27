package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Sine command into the command line, and converts the given value to its sine.
 * It assumes that the user provides one "child", or subsequent command -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double to get the Sine of, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties, and Java.Math classes/packages in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command sinCommand = new SineCommand();
 * double degreesToConvert = 45;
 * sinCommand.addChild(new ConstantCommand(degreesToConvert));
 * double sinOfDegreesToConvert = sinCommand.execute(turtle, globalParams);
 * ...
 *
 * A thing to note when using SineCommand.java is that when it is time to calculate the arcTan, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class SineCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public SineCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double degrees = getChildren().get(0).execute(turtle, globalProperties);

    return Math.toDegrees(Math.sin(Math.toRadians(degrees)));
  }
}

