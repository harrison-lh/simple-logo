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
    int param = 5;

    // TODO: set all turtles in the list to be active

    // TODO: return value of last turtle

    globalProperties.makeNewTurtles(param);

    return 0;
  }

}
