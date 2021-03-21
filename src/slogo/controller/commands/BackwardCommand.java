package slogo.controller.commands;

import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.model.Turtle;

/**
 * BackCommand is a type of Command that directs the Turtle to more backward a certain number of
 * pixels.
 *
 * @author Harrison Huang
 */
public class BackwardCommand extends Command {

  private static final int NUM_PARAMS = 1;

  /**
   * Constructor for the ForwardCommand.
   */
  public BackwardCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Executes the back command onto the turtle.
   *
   * @param turtle The turtle to be moved backward
   * @param globalProperties
   * @return The distance that the turtle moved backward
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    double distance = getChildren().get(0).execute(turtle, globalProperties);
    turtle.forward(-1 * distance);
    return distance;
  }
}
