package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Minus command into the command line, negates the value given.
 * It assumes that the user provides one "child", or subsequent command -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command minusCommand = new MinusCommand();
 *
 * double val = 50;
 * Command constant = new ConstantCommand(val);
 * minusCommand.addChild(constant);
 *
 * double negVal = minusCommand.execute(turtle, globalParams); // this will be -50 in this example
 * ...
 *
 * A thing to note when using MinusCommand.java is that when it is time to calculate the negative, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class MinusCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public MinusCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double a = getChildren().get(0).execute(turtle, globalProperties);

    return a * -1;

  }
}
