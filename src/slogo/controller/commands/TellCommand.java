package slogo.controller.commands;

import java.util.HashSet;
import java.util.Set;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.model.Turtle;

/**
 * This class is used when the user types the Tell command into the command line, and will set the future turtles which will respond to Commands
 *
 * It assumes that the user provides one "child", or subsequent command, which are in the form of a ListCommand and calling .execute on this
 * Command will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command tellCommand = new TellCommand();
 * Command turtleList = new ListCommandHead();
 * double turtleId = 1;
 * Command turtleIdCommandOne = new ConstantCommand(turtleId);
 * turtleList.addInnerChild(turtleIdCommandOne);
 *  turtleId = 2;
 *  Command turtleIdCommandTwo = new ConstantCommand(turtleId);
 *  turtleList.addInnerChild(turtleIdCommandTwo);
 *  turtleId = 3;
 *  Command turtleIdCommandThree = new ConstantCommand(turtleId);
 *  turtleList.addInnerChild(turtleIdCommandThree);
 *
 * askCommand.addChild(turtleList);
 *
 * askCommand.execute(turtle, globalParams);
 * // the turtles with ids 1, 2, and 3 will respond to future commands
 * ...
 *
 * A thing to note when using TellCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */

public class TellCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for TellCommand.
   */
  public TellCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Tells which turtles to run commands.
   *
   * @param turtle           The current active turtle
   * @param globalProperties The global properties
   * @return The value of the last turtle
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    ListCommandHead tellListCommand = (ListCommandHead) getChildren().get(0);
    Set<Integer> turtleIDsToUse = new HashSet<>();
    int lastTurtleValue = 0;
    int maxTurtleValue = 0;

    //Get a list of all the turtles to be used in the future, and make any needed turtles
    for (int i = 0; i < tellListCommand.getInnerChildren().size(); i++) {
      lastTurtleValue = (int) tellListCommand.getInnerChildren().get(i)
          .execute(turtle, globalProperties);
      turtleIDsToUse.add(lastTurtleValue);
      if (lastTurtleValue > maxTurtleValue) {
        maxTurtleValue = lastTurtleValue;
      }
    }

    if (maxTurtleValue >= globalProperties.getNumTurtlesCreated()) {
      globalProperties.makeNewTurtles(maxTurtleValue);
    }

    globalProperties.clearActiveTurtleIds();
    globalProperties.addMultipleActiveTurtleIds(turtleIDsToUse);

    return lastTurtleValue;
  }

}
