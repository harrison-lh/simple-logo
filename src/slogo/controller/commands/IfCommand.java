package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.model.Turtle;

/**
 * This class is used when the user types the If command into the command line, and will execute the commands given in the second parameter, a list, if the contditions
 * of the first parameter (a Command) are true.
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of a Command and a ListCommandHead and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command ifCommand = new IfCommand();
 *
 * Command conditionCommand = new EqualCommand();
 * Command xCorCommand = new XCoordinateCommand();
 * Command yCorCommand = new YCoordinateCommand();
 * conditionCommand.addChild(xCorCommand);
 * conditionCommand.addChild(yCorCommand);
 * ifCommand.addChild(conditionCommand);
 *
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * ifCommand.addChild(commandList);
 *
 * askCommand.execute(turtle, globalParams);
 * // if the turtle's x and y coordinates are the same, the turtle will move forward 50 pixels.
 * ...
 *
 * A thing to note when using IfCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class IfCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int EXPR_INDEX = 0;
  private static final int COMMAND_INDEX = 1;

  public IfCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double expr = getChildren().get(EXPR_INDEX).execute(turtle, globalProperties);

    if (expr != 0) {
      return ((ListCommandHead) getChildren().get(COMMAND_INDEX)).getInnerChildren().get(0)
          .execute(turtle, globalProperties);
    }
    return 0;


  }
}
