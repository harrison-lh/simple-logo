package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the And command into the command line, and will check to see if both values input after
 * are not equal to 0, in which case it will return 1.  Otherwise it will return 0.
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of Command and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command nonZeroOrCommand = new OrCommand();
 * Command zeroOrCommand = new OrCommand();
 * double firstValue = 45;
 * double zeroValue = 0;
 * Command firstConstant = new ConstantCommand(firstValue);
 * Command zeroConstant = new ConstantCommand(zeroValue);
 * nonZeroOrCommand.addChild(firstConstant);
 * nonZeroOrCommand.addChild(zeroConstant);
 * zeroOrCommand.addChild(zeroConstant);
 * zeroOrCommand.addChild(zeroConstant);
 *
 * double one = nonZeroOrCommand.execute(turtle, globalParams)
 * //one will have a value of 1
 * double zero = zeroOrCommand.execute(turtle, globalParams)
 * //zero will have a value of 0
 * ...
 *
 * A thing to note when using AndCommand.java is that when it is time to calculate the and-value, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Harrison Huang
 * @Author Cole Spector
 *
 */

public class OrCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for OrCommand.
   */
  public OrCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Checks whether at least one value is nonzero.
   *
   * @param turtle           The current turtle
   * @param globalProperties The global properties
   * @return 1 if one value is nonzero, else 0
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double firstVal = getChildren().get(0).execute(turtle, globalProperties);
    double secondVal = getChildren().get(1).execute(turtle, globalProperties);

    return ((firstVal != 0) || (secondVal != 0)) ? 1 : 0;
  }
}
