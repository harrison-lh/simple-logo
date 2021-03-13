package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ListCommandHead;
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
  protected double executeCommand(Turtle turtle) {
    double expr = getChildren().get(EXPR_INDEX).execute(turtle);

    if(expr != 0){
      return ( (ListCommandHead) getChildren().get(TRUE_INDEX)).execute(turtle);
    } else {
      return ( (ListCommandHead) getChildren().get(FALSE_INDEX)).execute(turtle);
    }


  }
}
