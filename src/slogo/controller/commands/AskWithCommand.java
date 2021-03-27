package slogo.controller.commands;

import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.TurtleController;
import slogo.model.Turtle;

/**
 * This class is used when the user types the AskWith command into the command line, and will "ask" all turtles which meet the condition set in first parameter (a list)
 * to execute a series of commands (the second parameter, also a list), and returns the value of the last run command on the last turtle.
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form of a ListCommandHead and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command askWithCommand = new AskWithCommand();
 *
 * Command conditionalList = new ListCommandHead();
 * Command conditionCommand = new EqualCommand();
 * Command xCorCommand = new XCoordinateCommand();
 * Command yCorCommand = new YCoordinateCommand();
 * conditionCommand.addChild(xCorCommand);
 * conditionCommand.addChild(yCorCommand);
 * conditionalList.addInnerChile(conditionCommand);
 * askWithCommand.addChild(conditionalList);
 *
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * askWithCommand.addChild(commandList);
 *
 * askCommand.execute(turtle, globalParams);
 * // all turtles with an equivalent x and y coordinates will move forward 50 pixels
 * ...
 *
 * A thing to note when using AskCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */

public class AskWithCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int TURTLE_CONDITION_INDEX = 0;
  private static final int COMMANDS_INDEX = 1;
  private static final int DEPENDANT_CONDITION_INDEX = 1;

  /**
   * Constructor for AskWithCommand.
   */
  public AskWithCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Runs commands on all turtles that satisfy the condition.
   *
   * @param turtle           The current active turtle
   * @param globalProperties The global properties
   * @return The result of the last command run
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    ListCommandHead ConditionalCommand = (ListCommandHead) getChildren()
        .get(TURTLE_CONDITION_INDEX);

    if (ConditionalCommand.getInnerChildren().size() != 1) {
      throw new IllegalArgumentException(
          "ILLEGAL ARGUMENT EXCEPTION:\nASK WITH COMMAND CALLED WITH MULTIPLE CONDITIONS!");
    }

    List<TurtleController> turtleArmy = globalProperties.getCopyOfTurtleArmy();
    double lastCommandValue = 0;

    for (TurtleController tc : turtleArmy) {
      Turtle tcTurtle = tc.getTurtle();
      double fullConditional = ConditionalCommand.execute(tcTurtle, globalProperties);
      if (fullConditional == 1) {
        lastCommandValue = getChildren().get(COMMANDS_INDEX).execute(tcTurtle, globalProperties);
      }
    }

    return lastCommandValue;
  }

}
