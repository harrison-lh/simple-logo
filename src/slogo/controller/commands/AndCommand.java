package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the And command into the command line.
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of double.  These
 * two children will be checked to see if they are both , and will throw an exception if this assumption isn't valid.
 * This class is dependant on the Turtle, GlobalProperties, and Java.Math classes/packages in order to function.
 *
 * Example Code:
 *
 * ...
 * Command arcTanCommand = new ArcTangentCommand();
 * double degreesToConvert = 45;
 * arcTanCommand.addChild(new ConstantCommand(degreesToConvert));
 * double arcTanOfDegreesToConvert = arcTanCommand.execute;
 * ...
 *
 * A thing to note when using ArcTangentCommand.java is that when it is time to calculate the arcTan, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */

public class AndCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for AndCommand.
   */
  public AndCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Checks whether both values are nonzero.
   *
   * @param turtle           The current turtle
   * @param globalProperties The global properties
   * @return 1 if both values are nonzero, else 0
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double firstVal = getChildren().get(0).execute(turtle, globalProperties);
    double secondVal = getChildren().get(1).execute(turtle, globalProperties);

    return ((firstVal != 0) && (secondVal != 0)) ? 1 : 0;
  }
}
