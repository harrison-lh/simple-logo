package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Sum command into the command line, and divides the two values given.
 * It assumes that the user provides two "children", or subsequent commands -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command sumCommand = new QuotientCommand();
 *
 * double firstVal = 50;
 * Command firstConstant = new ConstantCommand(firstVal);
 * sumCommand.addChild(firstConstant);
 * double secondVal = 2;
 * Command secondConstant = new ConstantCommand(secondVal);
 * sumCommand.addChild(secondConstant);
 *
 * double sum = sumCommand.execute(turtle, globalParams); // this will be 52 in this example
 * ...
 *
 * A thing to note when using SumCommand.java is that when it is time to calculate the sum, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class SumCommand extends Command {

  private static final int NUM_PARAMS = 2;

  public SumCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double a = getChildren().get(0).execute(turtle, globalProperties);
    double b = getChildren().get(1).execute(turtle, globalProperties);

    return a + b;

  }
}
