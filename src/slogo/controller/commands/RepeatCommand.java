package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * RepeatCommand implements the ability to repeat an list of Commands a number of times
 *
 * @author Marc Chmielewski
 * @author Cole Spector
 */
public class RepeatCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for the RepeatCommand.
   */
  public RepeatCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Implementation of the RepeatCommand execute method. Forces the children of the listNode to
   * repeat as many times as the value of parameter 0. Returns the value of the last operation.
   *
   * @param turtle           The turtle on which the Commands are acting
   * @param globalProperties
   * @return Returns the return value of the last operation.
   */
  @Override
  public double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    double repetitionNum = getChildren().get(0).execute(turtle, globalProperties);
    double lastVal = 0;
    turtle.getVars().setValue(":repcount", 1);
    for (int i = 0; i < repetitionNum; i++) {
      lastVal = getChildren().get(1).execute(turtle, globalProperties);
      turtle.getVars().setValue(":repcount", i);
    }
    return lastVal;
  }
}
