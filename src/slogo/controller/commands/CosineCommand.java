package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;
/**
 * This class is used when the user types the Cos command into the command line, and converts the given value to its cosine.
 * It assumes that the user provides one "child", or subsequent command -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double to get the Cosine of, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties, and Java.Math classes/packages in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command cosCommand = new CosineCommand();
 * double degreesToConvert = 45;
 * cosCommand.addChild(new ConstantCommand(degreesToConvert));
 * double cosOfDegreesToConvert = cosCommand.execute(turtle, globalParams);
 * ...
 *
 * A thing to note when using ArcTangentCommand.java is that when it is time to calculate the arcTan, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class CosineCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public CosineCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double degrees = getChildren().get(0).execute(turtle, globalProperties);

    return Math.toDegrees(Math.cos(Math.toRadians(degrees)));
  }
}

