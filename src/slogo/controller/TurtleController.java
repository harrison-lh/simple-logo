package slogo.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import slogo.model.Turtle;

/**
 * A general controller class for Turtles. Takes in a Queue of Commands to run and then commands the
 * Turtle to run them. Can have new Commands added to the Queue.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public class TurtleController {
  private Turtle turtle;
  private Queue<Command> commandQueue;
  private boolean executeCommands;

  /**
   * Constructs a new TurtleController with a fresh Turtle, an empty queue, and not-presently running
   * any Commands.
   */
  public TurtleController() {
    this.turtle = new Turtle();
    this.commandQueue = new LinkedList<>();
    this.executeCommands = false;
  }

  /**
   * Tells the Turtle to execute Commands until either the commandQueue is empty or executeCommands
   * is set to false;
   */
  public void runCommands() {
    while(!commandQueue.isEmpty() && executeCommands) {
      commandQueue.poll().execute(turtle);
    }
  }

  /**
   * Add a List of Commands to the commandQueue
   *
   * @param commands The List of Commands to add to the commandQueue.
   */
  public void pushCommands(List<Command> commands) {
    commandQueue.addAll(commands);
  }

  /**
   * Returns whether or not this TurtleController is primed to execute Commands.
   *
   * @return The execution status of this TurtleController.
   */
  public boolean isAllowedToExecute() {
    return executeCommands;
  }

  /**
   * Sets the execution status of this TurtleController.
   *
   * @param executeCommands The new execution status of this TurtleController.
   */
  public void setExecuteCommands(boolean executeCommands) {
    this.executeCommands = executeCommands;
  }
}
