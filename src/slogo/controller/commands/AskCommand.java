package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * Ask is a type of Command that executes commands on a given set of turtles.
 *
 * @author Harrison Huang
 */

public class AskCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for AskCommand.
   */
  public AskCommand() {
    // TODO: add lexer to be able to parse the input
    setNumParams(NUM_PARAMS);
  }

  /**
   * Runs commands on a specific set of turtles.
   *
   * @param turtle The current active turtle
   * @return The result of the last command run
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    // TODO: parse input

    // TODO: runs commands on all turtles in the given list

    // TODO: return value of last command run

    return 0;
  }

}
