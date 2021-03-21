package slogo.controller.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.model.Turtle;

public class UserCommand extends Command {
  private static final double NEW_VAR_NUM = Double.NEGATIVE_INFINITY;


  private static String defaultName = "";
  private static List<String> defaultParams = new ArrayList<>();
  private static ListCommandHead defaultCommands = new ListCommandHead();

  private Map<String, Double> oldVarValues = new HashMap<>();



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
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    for(int i = 0; i < getNumParams(); i++){
      double value = getChildren().get(i).execute(turtle, globalProperties);
      //System.out.println(value);
      //System.out.println(parameters.get(i));
      if(turtle.getVars().containsKey(parameters.get(i))){
        oldVarValues.put(parameters.get(i), turtle.getVars().getValue(parameters.get(i)));
      } else {
        oldVarValues.put(parameters.get(i), NEW_VAR_NUM);
      }

      turtle.getVars().setValue(parameters.get(i), value);
    }
    double retVal = commands.execute(turtle, globalProperties);

    for(int i = 0; i < getNumParams(); i++){
      if(oldVarValues.get(parameters.get(i)) == NEW_VAR_NUM){
        turtle.getVars().removeValue(parameters.get(i));
      } else {
        turtle.getVars().setValue(parameters.get(i), oldVarValues.get(parameters.get(i)));
      }
    }

    return retVal;
  }

  @Override
  public String toString(){
    return name;
  }
}
