package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * AskWith is a type of Command that executes commands on all turtles that satisfy a certain
 * condition.
 *
 * @author Harrison Huang
 */

public class AskWithCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for AskWithCommand.
   */
  public AskWithCommand() {
    // TODO: add lexer to be able to parse the input
    setNumParams(NUM_PARAMS);
  }

  /**
   * Runs commands on all turtles that satisfy the condition.
   *
   * @param turtle The current active turtle
   * @return The result of the last command run
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    // TODO: parse input

    // TODO: runs commands on all turtles that satisfy the condition

    // TODO: return value of last command run

    return 0;
  }

}
