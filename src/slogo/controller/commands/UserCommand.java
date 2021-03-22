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


  private static final String defaultName = "";
  private static final List<String> defaultParams = new ArrayList<>();
  private static final ListCommandHead defaultCommands = new ListCommandHead();

  private final Map<String, Double> oldVarValues = new HashMap<>();
  private final String fullCommand;
  private String name;
  private List<String> parameters;
  private ListCommandHead commands;

  public UserCommand() {
    this(defaultName, defaultName, defaultParams, defaultCommands);
  }

  public UserCommand(UserCommand commandToCopy) {
    this(commandToCopy.name, commandToCopy.fullCommand, commandToCopy.parameters,
        commandToCopy.commands);
  }

  public UserCommand(String name, String fullName, List<String> parameters,
      ListCommandHead commands) {
    this.name = name;
    this.fullCommand = fullName;
    this.parameters = parameters;
    this.commands = commands;
    setNumParams(parameters.size());
  }

  public String getName() {
    return name;
  }

  public String getFullCommand() {
    return fullCommand;
  }

  public List<String> getParameters() {
    return parameters;
  }

  public void updateCommand(UserCommand command) {
    this.name = command.name;
    this.parameters = command.parameters;
    this.commands = command.commands;
    setNumParams(parameters.size());
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    for (int i = 0; i < getNumParams(); i++) {
      double value = getChildren().get(i).execute(turtle, globalProperties);
      //System.out.println(value);
      //System.out.println(parameters.get(i));
      if (globalProperties.containsVariable(parameters.get(i))) {
        oldVarValues.put(parameters.get(i), globalProperties.getVariableValue(parameters.get(i)));
      } else {
        oldVarValues.put(parameters.get(i), NEW_VAR_NUM);
      }

      globalProperties.setVariableValue(parameters.get(i), value);
    }
    double retVal = commands.execute(turtle, globalProperties);

    for (int i = 0; i < getNumParams(); i++) {
      if (oldVarValues.get(parameters.get(i)) == NEW_VAR_NUM) {
        globalProperties.removeVariable(parameters.get(i));
      } else {
        globalProperties.setVariableValue(parameters.get(i), oldVarValues.get(parameters.get(i)));
      }
    }

    return retVal;
  }

  @Override
  public String toString() {
    return name;
  }
}
