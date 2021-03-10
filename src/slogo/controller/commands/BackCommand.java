package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * BackCommand is a type of Command that directs the Turtle to more backward a certain number of
 * pixels.
 *
 * @author Harrison Huang
 */
public class BackCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for the ForwardCommand.
   */
  public BackCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Executes the back command onto the turtle.
   *
   * @param turtle The turtle to be moved backward
   * @return The distance that the turtle moved backward
   */
  @Override
  public double execute(Turtle turtle) {
    assert(getChildren().size() == getNumParams());

    double distance = getChildren().get(0).execute(turtle);
    turtle.forward(-1 * distance);
    return distance;
  }
}
