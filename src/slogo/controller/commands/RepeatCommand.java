package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Repeat command into the command line, and will execute the commands given in the second parameter, a list, the amount of times
 * given in the first parameter (a Command)
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of a Command and a ListCommandHead and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command repeatCommand = new RepeatCommand();
 *
 * double repeatAmp = 3;
 * Command repeatConstant = new ConstantCommand(repeatAmt);
 * repeatCommand.addChild(repeatConstant);
 *
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * repeatCommand.addChild(commandList);
 *
 * repeatCommand.execute(turtle, globalParams);
 * // the turtle will move forward 50 pixels 3 times.
 * ...
 *
 * A thing to note when using RepeatCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class RepeatCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Init for RepeatCommand
   */
  public RepeatCommand() {
    setNumParams(NUM_PARAMS);
  }


  @Override
  public double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    double repetitionNum = getChildren().get(0).execute(turtle, globalProperties);
    double lastVal = 0;
    turtle.getVars().setValue(":repcount", 1);
    for (int i = 0; i < repetitionNum; i++) {
      lastVal = getChildren().get(1).execute(turtle, globalProperties);
      turtle.getVars().setValue(":repcount", i);
    }
    return lastVal;
  }
}
