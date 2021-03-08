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
  private boolean runCommands;

  public TurtleController() {
    this.turtle = new Turtle();
    this.commandQueue = new LinkedList<>();
    this.runCommands = false;
  }

  public void runCommands() {
    while(!commandQueue.isEmpty() && runCommands) {
      commandQueue.poll().execute(turtle);
    }
  }

  public void pushCommands(List<Command> commands) {
    commandQueue.addAll(commands);
  }


  public boolean isRunCommands() {
    return runCommands;
  }

  public void setRunCommands(boolean runCommands) {
    this.runCommands = runCommands;
  }
}
