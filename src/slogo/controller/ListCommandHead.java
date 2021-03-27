package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Turtle;
/**
 * This class is used when the user types the "[" symbol into the command line, and will start a list of inner Commands to run.
 *
 * There are no breaking assumptions within this class
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * double amtToTurn = 90;
 * Command turnConstant = new ConstantCommand(amtToTurn);
 * Command turnCommand = new RightCommand();
 * turnCommand.addChild(turnConstant);
 * commandList.addInnerChild(turnCommand);
 * commandList.execute(turtle, globalParams) // this will return 90 from the final command run, the turnCommand;
 * // the turtle will move forward 50 pixels, and then turn right 90 degrees.
 * ...
 *
 * A thing to note when using ListCommand.java is that commands encapsulated within the list should be added
 * with .addInnerChild, for .addChild will add a subsequent command.  Also, when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class ListCommandHead extends Command {

  protected List<Command> innerChildren;

  /**
   * This is the init method, which also initializes the inner children list.
   */
  public ListCommandHead() {
    innerChildren = new ArrayList<>();
  }

  /**
   * This method returns a List<> with all of the inner children of the ListCommand
   * @return a List<Command> of all the inner children of the list
   */
  public List<Command> getInnerChildren() {
    return innerChildren;
  }

  /**
   * This adds a inner child to the ListCommand (a command which will be executed when .execute is called on the ListCommandHead)
   * @param innerChild is the Command to be added to the List of inner children.
   */
  public void addInnerChild(Command innerChild) {
    this.innerChildren.add(innerChild);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double lastVal = 0;
    for (Command child : innerChildren) {
      lastVal = child.execute(turtle, globalProperties);
    }
    return lastVal;


  }

  @Override
  public String toString() {
    return "ListCommandHead";
  }
}
