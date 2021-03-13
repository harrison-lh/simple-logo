package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantCommand;
import slogo.model.Turtle;

/**
 * Home is a type of Command that places the Turtle at (0,0) and resets the heading to 90 degrees.
 *
 * @author Harrison Huang
 */

public class HomeCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for HomeCommand.
   */
  public HomeCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the position of the turtle to be at home (0,0) and the heading to be 90 degrees.
   *
   * @param turtle The turtle to be set
   * @return The distance the turtle moved
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    Command setHeading = new SetHeadingCommand();
    setHeading.addChild(new ConstantCommand(90));
    setHeading.execute(turtle);

    Command setXY = new SetPositionCommand();
    setXY.addChild(new ConstantCommand(0));
    setXY.addChild(new ConstantCommand(0));

    return setXY.execute(turtle);
  }

}
