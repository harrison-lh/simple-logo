package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.ConstantNode;
import slogo.model.Turtle;

/**
 * Home is a type of Command that places the Turtle at (0,0).
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
   * Sets the position of the turtle to be at home (0,0).
   *
   * @param turtle The turtle to be set
   * @return The distance the turtle moved
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    Command setXY = new SetPositionCommand();
    setXY.addChild(new ConstantNode(0));
    setXY.addChild(new ConstantNode(0));

    return setXY.execute(turtle);
  }

}