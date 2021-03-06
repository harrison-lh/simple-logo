package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
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
   * @param turtle           The turtle to be moved forward
   * @param globalProperties The global properties
   * @return The distance that the turtle moved
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    double distance = getChildren().get(0).execute(turtle, globalProperties);
    turtle.forward(distance);
    return distance;
  }
}
