package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ListNode;
import slogo.controller.ListNodeHead;
import slogo.controller.Node;
import slogo.controller.VariableNode;
import slogo.model.Turtle;
import slogo.model.Variables;

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
   * @return Returns the return value of the last operation.
   */
  @Override
  public double execute(Turtle turtle) {

    assert (getChildren().size() == getNumParams());

    VariableNode var = ( (VariableNode) ( (ListNodeHead) getChildren().get(0)).getInnerChildren().get(VAR_INDEX));
    double start = ( (ListNodeHead) getChildren().get(0)).getInnerChildren().get(START_INDEX).execute(turtle);
    double end = ( (ListNodeHead) getChildren().get(0)).getInnerChildren().get(END_INDEX).execute(turtle);
    double increment = ( (ListNodeHead) getChildren().get(0)).getInnerChildren().get(INCREMENT_INDEX).execute(turtle);

    double lastVal = 0;
    var.setValue(start);

    for(double i = start; i < end; i += increment){
      lastVal = getChildren().get(1).execute(turtle);
      var.setValue(i);
    }

    return lastVal;
  }
}
