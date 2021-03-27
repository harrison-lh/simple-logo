package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;
/**
 * This class is used when the user types the IfElse command into the command line, and will execute the commands given in the second parameter, a list, if the contditions
 * of the first parameter (a Command) are true, or the commands given in the third parameter (a List) if the condition is false.
 *
 * It assumes that the user provides three "children", or subsequent commands, which are in the form of a Command and two ListCommandHead and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command ifElseCommand = new IfElseCommand();
 *
 * Command conditionCommand = new EqualCommand();
 * Command xCorCommand = new XCoordinateCommand();
 * Command yCorCommand = new YCoordinateCommand();
 * conditionCommand.addChild(xCorCommand);
 * conditionCommand.addChild(yCorCommand);
 * ifCommand.addChild(conditionCommand);
 *
 * Command commandListIf = new ListCommandHead();
 * double amtToMoveIf = 50;
 * Command forwardConstant = new ConstantCommand(amtToMoveIf);
 * Command forwardCommand = new ForwardCommand();
 * forwardCommand.addChild(forwardConstant);
 * commandListIf.addInnerChild(forwardCommand);
 * ifCommand.addChild(commandListIf);
 *
 * Command commandListElse = new ListCommandHead();
 *  * double amtToMoveElse = 50;
 *  * Command backConstant = new ConstantCommand(amtToMoveElse);
 *  * Command backCommand = new BackwardCommand();
 *  * backCommand.addChild(backConstant);
 *  * commandListElse.addInnerChild(backCommand);
 *  * ifCommand.addChild(commandListElse);
 *
 * askCommand.execute(turtle, globalParams);
 * // if the turtle's x and y coordinates are the same, the turtle will move forward 50 pixels, if not, the turtle will move
 * // back 50 pixels.
 * ...
 *
 * A thing to note when using IfElseCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class IfElseCommand extends Command {

  private static final int NUM_PARAMS = 3;
  private static final int EXPR_INDEX = 0;
  private static final int TRUE_INDEX = 1;
  private static final int FALSE_INDEX = 2;

  public IfElseCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double expr = getChildren().get(EXPR_INDEX).execute(turtle, globalProperties);

    if (expr != 0) {
      return getChildren().get(TRUE_INDEX).execute(turtle, globalProperties);
    } else {
      return getChildren().get(FALSE_INDEX).execute(turtle, globalProperties);
    }


  }
}
