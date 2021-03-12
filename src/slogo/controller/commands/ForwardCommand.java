package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * ForwardCommand is a type of Command that directs the Turtle to more forward a certain number of
 * pixels.
 *
 * @author Harrison Huang
 */
public class ForwardCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for the ForwardCommand.
   */
  public ForwardCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Executes the forward command onto the turtle.
   *
   * @param turtle The turtle to be moved forward
   * @return The distance that the turtle moved
   */
  @Override
  public double execute(Turtle turtle) {

    assert(getChildren().size() == getNumParams());

    double distance = getChildren().get(0).execute(turtle);
    turtle.forward(distance);
    return distance;
  }
}
