package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;

/**
 * ListCommand is a concrete class that can hold a number of different Command trees in a List.
 * This will be particularly useful for user-defined Commands and for other looping constructs.
 *
 * @author Marc Chmielewski
 * @author Harrison Huang
 */
public class ListCommand extends Command {

  List<Command> commandList;

  /**
   * Constructs a new ListCommand with an empty List of Nodes.
   */
  public ListCommand() {
    commandList = new ArrayList<>();
  }

  /**
   * Constructs a new ListCommand with a List of Nodes
   * @param commandList The List of Nodes with which to construct the ListCommand
   */
  public ListCommand(List<Command> commandList) {
    this.commandList = commandList;
  }

  /**
   * The List of Nodes that is contained within this ListCommand
   *
   * @return The List of Nodes contained within this ListCommand
   */
  public List<Command> getCommandList() {
    return commandList;
  }

  /**
   * Appends the passed Command to the end of this ListCommand's commandList.
   *
   * @param command The Command to append ot the commandList
   */
  public void addCommandToList(Command command) {
    commandList.add(command);
  }

  /**
   * If this ListCommand's commandList contains the commandToRemove, remove the first instance of it.
   *
   * @param commandToRemove The Command to remove from the commandList.
   *
   * @return Whether or not the operation successfully removed the first instance of the Command.
   */
  public boolean removeCommandFromList(Command commandToRemove) {
    return commandList.remove(commandToRemove);
  }

  /**
   * Executes all Commands in the list. Returns the value of the last command run.
   * @param turtle The turtle to be commanded
   * @return The value of the last command run, or 0 if none are run.
   */
  @Override
  public double executeCommand(Turtle turtle) {
    double lastVal = 0;
    for (Command command : commandList) {
      lastVal = command.executeCommand(turtle);
    }
    return lastVal;
  }
}
