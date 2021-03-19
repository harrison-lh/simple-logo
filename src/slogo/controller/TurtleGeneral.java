package slogo.controller;

import java.util.List;
import slogo.model.Palette;
import slogo.model.Turtle;

/**
 * TurtleGeneral is a delightfully named marshalling class for controlling n-Turtles by way of their
 * TurtleControllers. Also contains global Turtle data like variables, user-defined functions, and
 * color palettes.
 *
 * @author Marc Chmielewski
 */
public class TurtleGeneral {
  List<TurtleController> turtleArmy;
  // TODO: Move control of Variables and UserCommands here
  Palette palette;
  int curTurtleId;

  public TurtleGeneral(List<TurtleController> turtleArmy) {
    this.turtleArmy = turtleArmy;
    palette = new Palette();
    curTurtleId = 0;
  }

  public void conscriptTurtleController(TurtleController recruitController) {
    turtleArmy.add(recruitController.getTurtle().getId(), recruitController);
  }
}
