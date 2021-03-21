package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

/**
 * MakeVariableCommand is a type of Command that handles user-defined variables in SLogo.
 *
 * @author Marc Chmielewski
 */
public class MakeVariableCommand extends Command {

  private static final int NUM_PARAMS = 2;

  public MakeVariableCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    turtle.getVars().setValue(((VariableCommand) getChildren().get(0)).getName(),
        getChildren().get(1).execute(turtle, globalProperties));
    return getChildren().get(1).execute(turtle, globalProperties);
  }
}
