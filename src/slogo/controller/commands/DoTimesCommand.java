package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

/**
 * This class is used when the user types the DoTimes command into the command line, and will repeat the commands given in the second parameter,
 * a list, as many times as defined in the first parameter, also a list, and returns the value of the last run command on the last turtle.
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of a ListCommand and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command doTimesCommand = new DoTimesCommand();
 *
 * Command numList = new ListCommandHead();
 * String varName = ":var"
 * Command varCommand = new VariableCommand(varName);
 * double repeatNum = 5;
 * Command repeatConstant = new ConstantCommand(repeatNum);
 * numList.addInnerChild(varCommand);
 * numList.addInnerChild(repeatConstant);
 * doTimesCommand.addChild(doTimesCommand);
 *
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * doTimesCommand.addChild(commandList);
 *
 * doTimesCommand.execute(turtle, globalParams);
 * // the turtle will move forward 50 pixels, 5 times.
 * ...
 *
 * A thing to note when using DoTimesCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class DoTimesCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int VAR_INDEX = 0;
  private static final int LIMIT_INDEX = 1;

  public DoTimesCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    VariableCommand var = ((VariableCommand) ((ListCommandHead) getChildren().get(0))
        .getInnerChildren().get(VAR_INDEX));
    double limit = ((ListCommandHead) getChildren().get(0)).getInnerChildren().get(LIMIT_INDEX)
        .execute(turtle, globalProperties);

    double lastVal = 0;
    var.setValue(1);

    for (double i = 1; i <= limit; i++) {
      lastVal = getChildren().get(1).execute(turtle, globalProperties);
      var.setValue(i);
    }

    return lastVal;

  }
}
