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


  public ToCommand(){
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle) {

    VariableCommand commandName = ( (VariableCommand) getChildren().get(NAME_INDEX));
    ListCommandHead variables = ( (ListCommandHead) getChildren().get(VAR_INDEX));
    for(Command command : variables.getInnerChildren()){
      VariableCommand varCom = (VariableCommand) command;
      turtle.getVars().setValue(varCom.getName(), varCom.getValue());
    }
    ListCommandHead commands = ( (ListCommandHead) getChildren().get(COMMANDS_INDEX));
    turtle.getVars().setValue(commandName.getName(), commands);

    return 0;
  }
}
