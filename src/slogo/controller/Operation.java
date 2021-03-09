package slogo.controller;

/**
 * Operation is the abstract ancestor of all other Operations. That is, operations that have no
 * direct effect on the Turtle. This will leverage reflection to allow the program to instantiate
 * the proper Operation on the fly, which will avoid use of the dreaded instanceof operator.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public abstract class Operation extends Node {

  int numOperands;

  /**
   * The execution behavior of the Operation. That is, what it does once the AST is being consumed.
   */
  public abstract void execute();

  /**
   * Returns the number of operands that this Operation takes
   *
   * @return The number of operands that this Operation takes
   */
  public int getNumOperands() {
    return numOperands;
  }
}
