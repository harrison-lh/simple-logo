package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;
import slogo.model.Variables;

public class ToCommand extends Command {

  private static final int NUM_PARAMS = 3;
  private static final int NAME_INDEX = 0;
  private static final int VAR_INDEX = 1;
  private static final int COMMANDS_INDEX = 2;

  @Override
  protected double executeCommand(Turtle turtle) {

    VariableCommand commandName = ( (VariableCommand) getChildren().get(0));


    return 0;
  }
}
