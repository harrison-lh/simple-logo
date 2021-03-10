package slogo.controller;

import java.util.ArrayDeque;
import java.util.Deque;
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
  private Deque<Node> commandQueue;
  private Deque<Node> commandHistory;
  private boolean executeCommands;

  /**
   * Constructs a new TurtleController with a fresh Turtle, an empty queue, and not-presently
   * running any Commands.
   */
  public TurtleController(Turtle turtle) {
    this.turtle = turtle;
    commandQueue = new ArrayDeque<>();
    commandHistory = new ArrayDeque<>();
  }

  /**
   * Tells the Turtle to execute Commands until either the commandQueue is empty.
   */
  public void runCommands() {
    while (!commandQueue.isEmpty()) {
      if (executeCommands) {
        commandHistory.add(commandQueue.peek());
        commandQueue.poll().execute(turtle);
      }
    }
  }

  /**
   * Add a Queue of Commands to the commandQueue
   *
   * @param commands The Queue of Commands to add to the commandQueue.
   */
  public void pushNodes(Queue<Node> commands) {
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
  public void setIsAllowedToExecute(boolean executeCommands) {
    this.executeCommands = executeCommands;
  }

  /**
   * Returns the Turtle held by this TurtleController
   *
   * @return The Turtle held by this TurtleController
   */
  public Turtle getTurtle() {
    return turtle;
  }
}
