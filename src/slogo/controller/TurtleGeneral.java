package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Palette;

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
  int curTurtleIndex;

  public TurtleGeneral() {
    turtleArmy = new ArrayList<>();
  }

}
