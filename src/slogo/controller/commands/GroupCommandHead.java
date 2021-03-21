package slogo.controller.commands;

import java.util.ArrayList;
import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class GroupCommandHead extends Command {

  private Command groupHeader;
  private List<List<Command>> headerChildren;

  public GroupCommandHead(){
    headerChildren = new ArrayList<>();
  }

  public void addNewHeaderChildrenList(){
    headerChildren.add(new ArrayList<>());
  }

  public void addNewHeaderChild(Command command){
    headerChildren.get(headerChildren.size() - 1).add(command);
  }


  public void setGroupHeader(Command groupHeader){
    this.groupHeader = groupHeader;
  }

  public Command getGroupHeader(){
    return groupHeader;
  }



  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double retVal = 0;
    for(List<Command> commandList : headerChildren){
      System.out.println(turtle.getX());
      groupHeader.clearChildren();
      for(Command command : commandList){
        groupHeader.addChild(command);
      }
      retVal += groupHeader.execute(turtle, globalProperties);

    }
    return retVal;
  }
}
