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

  /**
   * Execute the command, creating a new Variable and attaching it to the globalProperties.
   *
   * @param turtle The turtle onto which to execute the command
   * @param globalProperties The global properties to which to write the new Variable
   * @return The value of the Variable at initialization
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    globalProperties.setVariableValue(((VariableCommand) getChildren().get(0)).getName(),
        getChildren().get(1).execute(turtle, globalProperties));
    return getChildren().get(1).execute(turtle, globalProperties);
  }
}
