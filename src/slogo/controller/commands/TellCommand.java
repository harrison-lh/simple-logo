package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
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
   * @param turtle The current active turtle
   * @param globalProperties
   * @return The value of the last turtle
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    // TODO: parse input
    int numTurtles = (int)getChildren().get(0).execute(turtle, globalProperties);
    System.out.println("Number of turtles to make: " + numTurtles);

    globalProperties.makeNewTurtles(numTurtles);

    // TODO: set all turtles in the list to be active
    globalProperties.clearActiveTurtleIds();
    globalProperties.addActiveTurtleId(numTurtles);

    // TODO: return value of last turtle

    return numTurtles;
  }

}
