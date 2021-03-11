package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ListNode;
import slogo.controller.Node;
import slogo.controller.VariableNode;
import slogo.model.Turtle;

/**
 * ForCommand more or less implements for-loops in SLogo.
 *
 * @author Marc Chmielewski
 */
public class ForCommand extends Command {

  private static final int NUM_PARAMS = 2;

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

    Node node = ((ListNode) getChildren().get(0)).getNodeList().get(0);
    VariableNode variableNode = (VariableNode) node;
    String variableName = variableNode.getName();

    double initVal = ((ListNode) getChildren().get(0)).getNodeList().get(1).execute(turtle);
    turtle.getVars().setValue(variableName, initVal);
    double endVal = ((ListNode) getChildren().get(0)).getNodeList().get(2).execute(turtle);
    double incrementVal = ((ListNode) getChildren().get(0)).getNodeList().get(3).execute(turtle);

    double lastVal = 0.0;
    for (double i = initVal; i < endVal; i += incrementVal) {
      turtle.getVars().setValue(variableName, i);
      lastVal = getChildren().get(1).execute(turtle);
    }
    return lastVal;
  }
}
