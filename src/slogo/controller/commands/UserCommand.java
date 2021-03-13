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

  public UserCommand(String name, List<String> parameters, ListCommandHead commands){
    this.name = name;
    this.parameters = parameters;
    this.commands = commands;
    setNumParams(parameters.size());
  }

  public String getName(){
    return name;
  }

  @Override
  protected double executeCommand(Turtle turtle) {

    for(int i = 0; i < getNumParams(); i++){
      double value = getChildren().get(i).execute(turtle);
      turtle.getVars().setValue(parameters.get(i), value);
    }

    return commands.execute(turtle);
  }
}
