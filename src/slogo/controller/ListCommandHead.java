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
 * commandList.execute(turtle, globalParams);
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

  public ListCommandHead() {
    innerChildren = new ArrayList<>();
  }

  public List<Command> getInnerChildren() {
    return innerChildren;
  }

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
