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
  private static Map<String, Double> defaultParams = new HashMap<>();
  private static ListCommandHead defaultCommands = new ListCommandHead();



  private String name;
  private Map<String, Double> parameters;
  private ListCommandHead commands;

  public UserCommand(){
    this(defaultName, defaultParams, defaultCommands);
  }

  public UserCommand(String name, Map<String, Double> parameters, ListCommandHead commands){
    this.name = name;
    this.parameters = parameters;
    this.commands = commands;
    setNumParams(parameters.keySet().size());
  }



  @Override
  protected double executeCommand(Turtle turtle) {



    return commands.execute(turtle);
  }
}
