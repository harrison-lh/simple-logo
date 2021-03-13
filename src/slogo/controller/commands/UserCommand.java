package slogo.controller.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.controller.Command;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;
import slogo.model.Variables;

public class UserCommand extends Command {
  private static String defaultName = "";
  private static List<String> defaultParams = new ArrayList<>();
  private static ListCommandHead defaultCommands = new ListCommandHead();



  private String name;
  private List<String> parameters;
  private ListCommandHead commands;

  public UserCommand(){
    this(defaultName, defaultParams, defaultCommands);
  }

  public UserCommand(UserCommand commandToCopy){
    this(commandToCopy.name, commandToCopy.parameters, commandToCopy.commands);
  }

  public UserCommand(String name, List<String> parameters, ListCommandHead commands){
    this.name = name;
    this.parameters = parameters;
    this.commands = commands;
    setNumParams(parameters.size());
  }

  public String getName(){
    return name;
  }

  public void updateCommand(UserCommand command) {
    this.name = command.name;
    this.parameters = command.parameters;
    this.commands = command.commands;
    setNumParams(parameters.size());
  }

  @Override
  protected double executeCommand(Turtle turtle) {

    for(int i = 0; i < getNumParams(); i++){
      double value = getChildren().get(i).execute(turtle);
      System.out.println(value);
      System.out.println(parameters.get(i));
      turtle.getVars().setValue(parameters.get(i), value);
    }
    double retVal = commands.execute(turtle);

    return retVal;
  }

  @Override
  public String toString(){
    return name;
  }
}