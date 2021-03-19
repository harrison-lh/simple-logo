package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;

/**
 * Command represents the most basic unit of a parse-tree/AST. It is extended by other classes and
 * allows the program to leverage polymorphism to construct trees in an efficient manner. Command is
 * also the abstract ancestor of all other Commands. That is, commands which the Turtle can execute.
 * This will leverage reflection to allow the program to instantiate the proper Command on the fly,
 * which will avoid use of the dreaded instanceof operator.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public abstract class Command {

  private List<Command> children = new ArrayList<>();
  private int numParams = 0;
  private boolean isCollectionEnd = false;


  /**
   * Add an additional child Command to the Command.
   *
   * @param child The Command to add to the children
   */
  public void addChild(Command child) {
    children.add(child);
  }

  public void setIsCollectionEnd(boolean isListEnd){
    this.isCollectionEnd = isListEnd;
  }

  public void clearChildren(){
    children.clear();
  }

  public boolean getIsCollectionEnd(){
    return isCollectionEnd;
  }

  /**
   * Simple getter to return the list of child Commands
   *
   * @return A List of child Commands
   */
  public List<Command> getChildren() {
    return children;
  }

  /**
   * Executes commands. Runs an additional check that the command has the correct number of
   * parameters in order to execute the command.
   *
   * @param turtle The turtle on which to run the command
   * @return The double for the return value of each execute
   */
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());
    return executeCommand(turtle);
  }

  /**
   * The execution behavior of the Command. That is, what it does once the AST is being consumed.
   *
   * @return The double for the return value of each execute
   */
  protected abstract double executeCommand(Turtle turtle);

  /**
   * Sets the number of parameters that the Command takes.
   *
   * @param numParams The number of parameters the Command takes
   */
  protected void setNumParams(int numParams) {
    this.numParams = numParams;
  }

  /**
   * Returns the number of parameters that this Command has.
   *
   * @return The number of parameters that this Command has.
   */
  public int getNumParams() {
    return numParams;
  }

}
