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
  private final List<TurtleController> turtleArmy;
  // TODO: Move control of Variables and UserCommands here
  public static Palette palette;
  private int activeTurtleId;

  public TurtleGeneral(List<TurtleController> turtleArmy) {
    this.turtleArmy = turtleArmy;
    palette = new Palette();
    activeTurtleId = 0;
  }

  public void conscriptTurtle(Turtle recruitTurtle) {
    if(turtleArmy.size() < recruitTurtle.getId()) {
      for(int i = turtleArmy.size(); i <= recruitTurtle.getId(); i++) {
        // TODO: Implement turtle conscription behavior (REQUIRES FIXED LISTENERS)
      }
    }
  }

  public Palette getPalette() {
    return palette;
  }

  public int getActiveTurtleId() {
    return activeTurtleId;
  }

  public void setActiveTurtleId(int activeTurtleId) {
    this.activeTurtleId = activeTurtleId;
  }
}
