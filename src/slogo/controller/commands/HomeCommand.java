package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantCommand;
import slogo.model.Turtle;

/**
 * Home is a type of Command that places the Turtle at home (0,0) and resets the heading to the
 * default.
 *
 * @author Harrison Huang
 */

public class HomeCommand extends Command {

  private static final int NUM_PARAMS = 0;
  private static final double HOME_X_COORDINATE = 0;
  private static final double HOME_Y_COORDINATE = 0;
  private static final double DEFAULT_HEADING = 90;

  /**
   * Constructor for HomeCommand.
   */
  public HomeCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the position of the turtle to be at home (0,0) and the heading to be the default.
   *
   * @param turtle The turtle to be set
   * @return The distance the turtle moved
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    Command setHeading = new SetHeadingCommand();
    setHeading.addChild(new ConstantCommand(DEFAULT_HEADING));
    setHeading.execute(turtle);

    Command setXY = new SetPositionCommand();
    setXY.addChild(new ConstantCommand(HOME_X_COORDINATE));
    setXY.addChild(new ConstantCommand(HOME_Y_COORDINATE));

    return setXY.execute(turtle);
  }

}
