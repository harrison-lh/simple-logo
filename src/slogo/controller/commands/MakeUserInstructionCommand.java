package slogo.controller.commands;

import java.util.ArrayList;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.Lexer;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

/**
 * This class is used when the user types the MakeUserInstruction command into the command line, and will run the commands given in the first parameter (a list) with the parameters
 * given in the second parameter (also a list) whenever the string "name" is called
 *
 * It assumes that the user provides two "children", or subsequent commands, which are in the form  ListCommandHead and calling .execute on these
 * Commands will return a double.  These two children will be checked to see if they are both present, and will throw an exception if this assumption isn't upheld.
 * This class is dependant on the Turtle, ListCommandHead and GlobalProperties classes in order to function.
 *
 * Example Code:
 *
 * ... // assume to have previously made a Turtle.java object named turtle, a GlobalParameters.java object named globalParams, and a
 * // Lexer.java object named lexer.
 *
 * Command toCommand = new MakeUserInstructionCommand("foo", lexer, "foo [ ] [ fd 50 ]"); // the final parameter is specific to the current parser, and should
 *  // be swapped with an initialization method which does not have the final parameter for abstractability.
 *
 * Command parameterList = new ListCommandHead(); // this command will have no parameters
 * toCommand.addChild(parameterList);
 *
 * Command commandList = new ListCommandHead();
 * double amtToMove = 50;
 * Command moveConstant = new ConstantCommand(amtToMove);
 * Command moveCommand = new ForwardCommand();
 * moveCommand.addChild(moveConstant);
 * commandList.addInnerChild(moveCommand);
 * toCommand.addChild(commandList);
 *
 *
 * toCommand.execute(turtle, globalParams);
 * // creates a UserCommand with the name "foo", that when called moves the turtle forward 50 pixels.
 * ...
 *
 * A thing to note when using IfElseCommand.java is that when it is time to run, .execute should be called
 * and not .executeCommand, for .execute has the check for NUM_PARAMS.
 *
 * @Author Cole Spector
 *
 */
public class MakeUserInstructionCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int VAR_INDEX = 0;
  private static final int COMMANDS_INDEX = 1;
  private final Lexer lexer;
  private String name = "";
  private String fullCommand = "";

  public MakeUserInstructionCommand(String name, Lexer lexer, String initialingCommandString) {
    this.name = name;
    this.lexer = lexer;
    fullCommand = initialingCommandString;
    setNumParams(NUM_PARAMS);
  }

  /**
   * This gets the full command used to create the new command
   * @return a String, which is the contents of the string used to create this command.
   */
  public String getFullCommand() {
    return fullCommand;
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    try {
      ArrayList<String> varNames = new ArrayList<>();
      ListCommandHead variables = ((ListCommandHead) getChildren().get(VAR_INDEX));
      for (Command command : variables.getInnerChildren()) {
        VariableCommand varCom = (VariableCommand) command;
        varNames.add(varCom.getName());
      }
      ListCommandHead commands = ((ListCommandHead) getChildren().get(COMMANDS_INDEX));

      if (lexer.containsUserCommand(name)) {
        lexer.deleteUserCommand(name);
      }
      lexer.addUserCommand(new UserCommand(name, fullCommand, varNames, commands));

      return 1;
    } catch (Exception e) {
      //System.out.println("failed");
      return 0;
    }

  }
}
