package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.VariableCommand;
import slogo.model.Turtle;

/**
 * ForCommand more or less implements for-loops in SLogo.
 *
 * @author Marc Chmielewski
 */
public class ForCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int VAR_INDEX = 0;
  private static final int START_INDEX = 1;
  private static final int END_INDEX = 2;
  private static final int INCREMENT_INDEX = 3;

  public ForCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Implementation of the ForCommand, which is functionally equivalent to SLogo for-loops.
   *
   * @param turtle The turtle on which the Commands are acting
   * @param globalProperties
   * @return Returns the return value of the last operation.
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    VariableCommand varCommand = ( (VariableCommand) ( (ListCommandHead) getChildren().get(0)).getInnerChildren().get(VAR_INDEX));
    double start = ( (ListCommandHead) getChildren().get(0)).getInnerChildren().get(START_INDEX).execute(turtle, globalProperties);
    double end = ( (ListCommandHead) getChildren().get(0)).getInnerChildren().get(END_INDEX).execute(turtle, globalProperties);
    double increment = ( (ListCommandHead) getChildren().get(0)).getInnerChildren().get(INCREMENT_INDEX).execute(turtle, globalProperties);

    double lastVal = 0;
    varCommand.setValue(start);
    turtle.getVars().setValue(varCommand.getName(), varCommand.getValue());

    for(double i = start; i < end; i += increment){
      turtle.getVars().setValue(varCommand.getName(), i);
      lastVal = getChildren().get(1).execute(turtle, globalProperties);
    }

    return lastVal;
  }

}
