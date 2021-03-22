package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

public class DoTimesCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int VAR_INDEX = 0;
  private static final int LIMIT_INDEX = 1;

  public DoTimesCommand() {
    setNumParams(NUM_PARAMS);
  }

  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    VariableCommand var = ((VariableCommand) ((ListCommandHead) getChildren().get(0))
        .getInnerChildren().get(VAR_INDEX));
    double limit = ((ListCommandHead) getChildren().get(0)).getInnerChildren().get(LIMIT_INDEX)
        .execute(turtle, globalProperties);

    double lastVal = 0;
    var.setValue(1);

    for (double i = 1; i <= limit; i++) {
      lastVal = getChildren().get(1).execute(turtle, globalProperties);
      var.setValue(i);
    }

    return lastVal;

  }
}
