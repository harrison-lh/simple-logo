package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Product command into the command line, and multiplies the two values given.
 * It assumes that the user provides two "children", or subsequent commands -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command productCommand = new ProductCommand();
 *
 * double firstVal = 4;
 * Command firstConstant = new ConstantCommand(firstVal);
 * productCommand.addChild(firstConstant);
 * double secondVal = 5;
 * Command secondConstant = new ConstantCommand(secondVal);
 * productCommand.addChild(secondConstant);
 *
 * double product = productCommand.execute(turtle, globalParams); // this will be 20 in this example
 * ...
 *
 * A thing to note when using ProductCommand.java is that when it is time to calculate the difference, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class ProductCommand extends Command {

  private static final int NUM_PARAMS = 2;

  public ProductCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double a = getChildren().get(0).execute(turtle, globalProperties);
    double b = getChildren().get(1).execute(turtle, globalProperties);

    return a * b;

  }
}
