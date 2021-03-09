package slogo.controller.commands;

import slogo.controller.Node;
import slogo.model.Turtle;

/**
 * Command is the abstract ancestor of all other Commands. That is, commands which the Turtle can
 * execute. This will leverage reflection to allow the program to instantiate the proper Command
 * on the fly, which will avoid use of the dreaded instanceof operator.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public abstract class Command extends Node {

  protected int numParams;

  /**
   * The execution behavior of the Command. That is, what it does once the AST is being consumed.
   */
  public abstract void execute(Turtle turtle);

  /**
   * Returns the number of parameters that this Command takes
   * @return The number of parameters that this Command takes
   */
  public int getNumParams() {
    return numParams;
  }
}
