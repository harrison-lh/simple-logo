package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

/**
 * This class is used when the user types the For command into the command line, and will repeat the commands given in the second parameter,
 * a list, as many times as defined in the first parameter, also a list, and returns the value of the last run command on the last turtle.
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of a ListCommand and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * It also assumes that the first parameter-list has 4 values, the first of which is a variable, and the last three are commands which will evaluate to a double.
 *
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command forCommand = new ForCommand();
 *
 * Command numList = new ListCommandHead();
 * String varName = ":var"
 * Command varCommand = new VariableCommand(varName);
 * double startNum = 0;
 * Command startConstant = new ConstantCommand(startNum);
 * double endNum = 5;
 * Command endConstant = new ConstantCommand(endNum);
 * double incrementNum = 1;
 * Command incrementConstant = new ConstantCommand(incrementNum);
 * numList.addInnerChild(varCommand);
 * numList.addInnerChild(startConstant);
 * numList.addInnerChild(endConstant);
 * numList.addInnerChild(incrementConstant);
 * doTimesCommand.addChild(numList);
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
 * A thing to note when using ForCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class ForCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int VAR_INDEX = 0;
  private static final int START_INDEX = 1;
  private static final int END_INDEX = 2;
  private static final int INCREMENT_INDEX = 3;

  public ForCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Implementation of the ForCommand, which is functionally equivalent to SLogo for-loops.
   *
   * @param turtle           The turtle on which the Commands are acting
   * @param globalProperties The global properties
   * @return Returns the return value of the last operation.
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    VariableCommand varCommand = ((VariableCommand) ((ListCommandHead) getChildren().get(0))
        .getInnerChildren().get(VAR_INDEX));
    double start = ((ListCommandHead) getChildren().get(0)).getInnerChildren().get(START_INDEX)
        .execute(turtle, globalProperties);
    double end = ((ListCommandHead) getChildren().get(0)).getInnerChildren().get(END_INDEX)
        .execute(turtle, globalProperties);
    double increment = ((ListCommandHead) getChildren().get(0)).getInnerChildren()
        .get(INCREMENT_INDEX).execute(turtle, globalProperties);

    double lastVal = 0;
    varCommand.setValue(start);
    globalProperties.setVariableValue(varCommand.getName(), varCommand.getValue());

    for (double i = start; i < end; i += increment) {
      globalProperties.setVariableValue(varCommand.getName(), i);
      lastVal = getChildren().get(1).execute(turtle, globalProperties);
    }

    return lastVal;
  }

}
