package slogo.controller.commands;

import java.util.ArrayList;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.Lexer;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

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
