package slogo.controller.commands;

import java.util.ArrayList;
import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;


/**
 * This class is used when the user types the "(" symbol into the command line, and will start a group of inner Commands to run.
 *
 * This class assumes that the user calls addNewHeaderChildrenList for each new group of Children for the head command to run.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, and a GlobalParameters.java object named globalParams
 * Command groupList = new GroupCommandHead();
 *
 * Command moveCommand = new ForwardCommand();
 * groupList.setGroupHeader(moveCommand);
 *
 * double amtToMove = 10;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * groupList.addNewHeaderChildrenList();
 * groupList.addNewHeaderChild(moveConstant);
 *
 * amtToMove = 20;
 * moveConstant = new ConstantCommand(amtToMove);
 * groupList.addNewHeaderChildrenList();
 * groupList.addNewHeaderChild(moveConstant);
 *
 * amtToMove = 30;
 * moveConstant = new ConstantCommand(amtToMove);
 * groupList.addNewHeaderChildrenList();
 * groupList.addNewHeaderChild(moveConstant);
 *
 * commandList.execute(turtle, globalParams) // this will return 30 from the final command run, the turnCommand;
 * // the turtle will move forward 10 pixels, then 20 pixels, then 30 pixels.
 * ...
 *
 * A thing to note when using GroupCommandHead.java is that the header command should be set with .setGroupHeader,
 * and the values for the header should be added with the combination of .addNewHeaderChildrenList and .addNewHeaderChild
 * Also, when it is time to run, .execute should be called and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class GroupCommandHead extends Command {

  private final List<List<Command>> headerChildren;
  private Command groupHeader;

  /**
   * This is the init for GroupCommandHead, and also initializes the header children List
   */
  public GroupCommandHead() {
    headerChildren = new ArrayList<>();
  }

  /**
   * This command creates a new inner List within the header children containing List, which is used to add the contents as Children to
   * the header later
   */
  public void addNewHeaderChildrenList() {
    headerChildren.add(new ArrayList<>());
  }

  /**
   * This adds the parameter Command to the inner list (created by addNewHeaderChildrenList)
   * @param command is a Command which will be used as a Child for the Header.
   */
  public void addNewHeaderChild(Command command) {
    headerChildren.get(headerChildren.size() - 1).add(command);
  }

  /**
   * This method returns the group Header command
   * @return the Command currently set as the group header
   */
  public Command getGroupHeader() {
    return groupHeader;
  }

  /**
   * this method sets the Group header Command, or the command that will be run with all the subsequent children.
   * @param groupHeader is the Command to set as the group's header
   */
  public void setGroupHeader(Command groupHeader) {
    this.groupHeader = groupHeader;
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double retVal = 0;
    for (List<Command> commandList : headerChildren) {
      //System.out.println(turtle.getX());
      groupHeader.clearChildren();
      for (Command command : commandList) {
        groupHeader.addChild(command);
      }
      retVal += groupHeader.execute(turtle, globalProperties);

    }
    return retVal;
  }
}
