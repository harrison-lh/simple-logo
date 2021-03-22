package slogo.controller.commands;

import java.util.HashSet;
import java.util.Set;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.model.Turtle;

/**
 * Tell is a type of Command that tells which turtles should follow commands.
 *
 * @author Harrison Huang
 */

public class TellCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for TellCommand.
   */
  public TellCommand() {
    // TODO: add lexer to be able to parse the input
    setNumParams(NUM_PARAMS);
  }

  /**
   * Tells which turtles to run commands.
   *
   * @param turtle           The current active turtle
   * @param globalProperties
   * @return The value of the last turtle
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    // TODO: parse input

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

    // TODO: set all turtles in the list to be active
    globalProperties.clearActiveTurtleIds();
    globalProperties.addMultipleActiveTurtleIds(turtleIDsToUse);

    // TODO: return value of last turtle

    return lastTurtleValue;
  }

}
