package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.VariableNode;
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
  public double execute(Turtle turtle) {
    assert (getChildren().size() == getNumParams());
    turtle.getVars().setValue(((VariableNode) getChildren().get(0)).getName(),
        getChildren().get(1).execute(turtle));
    return getChildren().get(1).execute(turtle);
  }
}
