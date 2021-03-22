package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.model.Turtle;

public class IfCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int EXPR_INDEX = 0;
  private static final int COMMAND_INDEX = 1;

  public IfCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double expr = getChildren().get(EXPR_INDEX).execute(turtle, globalProperties);

    if (expr != 0) {
      return ((ListCommandHead) getChildren().get(COMMAND_INDEX)).getInnerChildren().get(0)
          .execute(turtle, globalProperties);
    }
    return 0;


  }
}
