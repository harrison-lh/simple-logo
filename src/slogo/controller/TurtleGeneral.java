package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import slogo.model.GridCoordinates;
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
  private Palette palette;
  private final List<Integer> activeTurtleIds;
  private Consumer<TurtleProperties> newTurtleConsumer;
  private final GlobalProperties globalProperties;

  public TurtleGeneral() {
    this.turtleArmy = new ArrayList<>();
    palette = new Palette();
    activeTurtleIds = new ArrayList<>();
    for(TurtleController curController : turtleArmy) {
      activeTurtleIds.add(curController.getTurtle().getId());
    }
    globalProperties = new GlobalProperties(palette.getColorsProperty());
    newTurtleConsumer = (param) -> {}; // Fixes headless tests breaking by adding a dummy
  }

  public void conscriptTurtle(TurtleController recruitTurtle) {
    turtleArmy.add(recruitTurtle);
    activeTurtleIds.add(recruitTurtle.getTurtle().getId());
    newTurtleConsumer.accept(new TurtleProperties(recruitTurtle.getTurtle()));

    if(turtleArmy.size() < recruitTurtle.getTurtle().getId()) {
      for(int i = turtleArmy.size(); i < recruitTurtle.getTurtle().getId(); i++) {
        Turtle freshTurtle = new Turtle(i, new GridCoordinates());
        TurtleController freshTurtleController = new TurtleController(freshTurtle, globalProperties);
        turtleArmy.add(freshTurtleController);
        activeTurtleIds.add(freshTurtleController.getTurtle().getId());
        newTurtleConsumer.accept(new TurtleProperties(freshTurtleController.getTurtle()));
      }
    }
  }

  public Palette getPalette() {
    return palette;
  }

  public List<Integer> getActiveTurtleIds() {
    return activeTurtleIds;
  }

  public void setActiveTurtles(List<Integer> activeTurtleIds) {
    this.activeTurtleIds.clear();
    this.activeTurtleIds.addAll(activeTurtleIds);
  }

  public List<TurtleController> getTurtleArmy() {
    return turtleArmy;
  }

  public void setNewTurtleConsumer(Consumer<TurtleProperties> newTurtleConsumer) {
    this.newTurtleConsumer = newTurtleConsumer;
  }

  public GlobalProperties getGlobalProperties() {
    return globalProperties;
  }
}
