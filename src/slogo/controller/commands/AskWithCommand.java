package slogo.controller.commands;

import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.TurtleController;
import slogo.model.Turtle;

/**
 * AskWith is a type of Command that executes commands on all turtles that satisfy a certain
 * condition.
 *
 * @author Harrison Huang
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
