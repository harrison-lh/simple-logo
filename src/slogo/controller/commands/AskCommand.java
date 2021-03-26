package slogo.controller.commands;

import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.TurtleController;
import slogo.model.Turtle;

/**
 * This class is used when the user types the And command into the command line, and will "ask" all turtles listed in the first parameter (a list)
 * to execute a series of commands (the second parameter, also a list), and returns the value of the last run command on the last turtle.
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of a ListCommand and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command askCommand = new AskCommand();
 * Command turtleList = new ListCommandHead();
 * double turtleId = 1;
 * Command turtleIdCommand = new ConstantCommand(turtleId);
 * turtleList.addInnerChild(turtleIdCommand);
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * askCommand.addChild(turtleList);
 * askCommand.addChild(commandList);
 * askCommand.execute(turtle, globalParams);
 * // the turtle with id=1 will move forward 50 pixels
 * ...
 *
 * A thing to note when using AskCommand.java is that when it is time to calculate the and-value, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */

public class AskCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int TURTLE_IDS_INDEX = 0;
  private static final int COMMANDS_INDEX = 1;


  /**
   * Constructor for AskCommand.
   */
  public AskCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Runs commands on a specific set of turtles.
   *
   * @param turtle           The current active turtle
   * @param globalProperties The global properties
   * @return The result of the last command run
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    ListCommandHead askListCommand = (ListCommandHead) getChildren().get(TURTLE_IDS_INDEX);

    List<TurtleController> turtleArmy = globalProperties.getCopyOfTurtleArmy();
    double lastCommandValue = 0;

    for (int i = 0; i < askListCommand.getInnerChildren().size(); i++) {
      int turtleID = (int) askListCommand.getInnerChildren().get(i)
          .execute(turtle, globalProperties);
      if (turtleID <= globalProperties.getNumTurtlesCreated()) {
        for (TurtleController tc : turtleArmy) {
          if (tc.getTurtle().getId() == turtleID) {
            lastCommandValue = getChildren().get(COMMANDS_INDEX)
                .execute(tc.getTurtle(), globalProperties);
            continue;
          }
        }
      }
    }

    return lastCommandValue;
  }

}
