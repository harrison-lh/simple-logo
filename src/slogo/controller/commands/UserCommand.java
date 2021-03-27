package slogo.controller.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.model.Turtle;

/**
 * This class is used when the user types the name of a command previously made with MakeUserInstructionCommand into the command line, or for personal
 * uses a UserCommand could be defined independently, and will run whenever the string "name" is called
 *
 * There are no breaking assumptions for this class
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, a GlobalParameters.java object named globalParams
 *
 *
 * List<String> parameterList = new List<>(); // this command will have no parameters
 *
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 *
 * Command userCommand = new UserCommand("foo", "foo [ ] [ fd 50 ]", parameterList, commandList); // the second parameter is specific to the current parser, and should
 * be swapped with an initialization method which does not have the second parameter for abstractability.
 *
 * userCommand.execute(turtle, globalParams);
 * // moves the turtle forward 50 pixels.
 * ...
 *
 * A thing to note when using UserCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
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

  /**
   * Default UserCommand init
   */
  public UserCommand() {
    this(defaultName, defaultName, defaultParams, defaultCommands);
  }


  /**
   * A UserCommand initialization which copies the content of a different UserCommand
   * @param commandToCopy is a previously made UserCommand
   */
  public UserCommand(UserCommand commandToCopy) {
    this(commandToCopy.name, commandToCopy.fullCommand, commandToCopy.parameters,
        commandToCopy.commands);
  }

  /**
   * The full UserCommand initializer, which takes in a String for the Command name, the String used to create the command
   * in the parser, a List of parameters, and a ListCommand of Commands to run.
   * @param name is the name which can later be used to call this UserCommand
   * @param fullName is the full string representing the CommandLine input to create the command
   * @param parameters is a List <String> of the parameters for this UserCommand
   * @param commands is the ListCommandHead which contains all the Commands which will be run when this Command is run
   */
  public UserCommand(String name, String fullName, List<String> parameters,
      ListCommandHead commands) {
    this.name = name;
    this.fullCommand = fullName;
    this.parameters = parameters;
    this.commands = commands;
    setNumParams(parameters.size());
  }

  /**
   * Returns the name of this UserCommand
   * @return a String, the name of this UserCommand
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the String used to create this UserCommand in the commandLine
   * @return a String, the String used to create this UserCommand in the commandLine
   */
  public String getFullCommand() {
    return fullCommand;
  }

  /**
   * Returns the List<String> of parameters used in this UserCommand
   * @return a List<String> of the parameters used in this UserCommand
   */
  public List<String> getParameters() {
    return parameters;
  }

  /**
   * Similar to the copy initializer, this method copies the contents from another command into this command.
   * @param command a previously created user command to copy.
   */
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
