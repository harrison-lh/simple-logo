package slogo.controller;

import java.util.List;
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

  /**
   * The execution behavior of the Command. That is, what it does once the AST is being consumed.
   *
   * @return The double for the return value of the command
   */
  public abstract double execute(Turtle turtle);

}
