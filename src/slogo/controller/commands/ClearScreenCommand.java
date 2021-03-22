package slogo.controller.commands;

import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.TurtleController;
import slogo.model.Turtle;

/**
 * ClearScreen is a type of Command that places the Turtle at (0,0) and removes all lines.
 *
 * @author Harrison Huang
 */

public class ClearScreenCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for ClearScreenCommand.
   */
  public ClearScreenCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Sets the position of the turtle to be at home (0,0) and removes all lines.
   *
   * @param turtle The turtle to be set
   * @param globalProperties
   * @return The distance the turtle moved
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {
    List<TurtleController> turtleArmy = globalProperties.getCopyOfTurtleArmy();

    double distance = 0;
    for(TurtleController tc : turtleArmy){
      Command goHome = new HomeCommand();
      distance = goHome.execute(tc.getTurtle(), globalProperties);
      Command penDown = new PenDownCommand();
      penDown.execute(tc.getTurtle(), globalProperties);
    }
    globalProperties.clearScreen();
    return distance;
  }

}
