package slogo.controller.commands;

import java.util.concurrent.ThreadLocalRandom;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;
/**
 * This class is used when the user types the Random command into the command line, and gives a random value.
 * It assumes that the user provides one "child", or subsequent command -- most likely a constant -- which are of type Command, and calling .execute on these
 * Commands will return a double, and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties, and Java.ThreadLocalRandom.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command randCommand = new RandomCommand();
 *
 * double val = 50;
 * Command constant = new ConstantCommand(val);
 * randCommand.addChild(constant);
 *
 * double randVal = randCommand.execute(turtle, globalParams); // this will be a random double between 0 and 50 in this example
 * ...
 *
 * A thing to note when using RandomCommand.java is that when it is time to calculate the negative, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class RandomCommand extends Command {

  private static final int NUM_PARAMS = 1;

  public RandomCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double bound = getChildren().get(0).execute(turtle, globalProperties);

    return ThreadLocalRandom.current().nextDouble(0, bound);

  }
}

