package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

public class IfElseCommand extends Command {

  private static final int NUM_PARAMS = 3;
  private static final int EXPR_INDEX = 0;
  private static final int TRUE_INDEX = 1;
  private static final int FALSE_INDEX = 2;

  public IfElseCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double expr = getChildren().get(EXPR_INDEX).execute(turtle, globalProperties);

    if (expr != 0) {
      return getChildren().get(TRUE_INDEX).execute(turtle, globalProperties);
    } else {
      return getChildren().get(FALSE_INDEX).execute(turtle, globalProperties);
    }


  }
}
