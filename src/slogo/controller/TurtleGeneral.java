package slogo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import slogo.model.GridCoordinates;
import slogo.model.Palette;
import slogo.model.Turtle;
import slogo.model.Variables;

/**
 * TurtleGeneral is a delightfully named marshalling class for controlling n-Turtles by way of their
 * TurtleControllers. Also contains global Turtle data like variables, user-defined functions, and
 * color palettes.
 *
 * @author Marc Chmielewski
 */
public class TurtleGeneral {

  private final List<TurtleController> turtleArmy;
  private final List<TurtleController> turtleRecruits;
  // TODO: Move control of Variables and UserCommands here
  private final Variables variables;
  private Palette palette;
  private Consumer<TurtleProperties> newTurtleConsumer;
  private final GlobalProperties globalProperties;

  public TurtleGeneral() {
    this.turtleArmy = new ArrayList<>();
    palette = new Palette();
    turtleRecruits = new ArrayList<>();
    variables = new Variables();
    globalProperties = new GlobalProperties(palette.getColorsProperty(), variables,
        turtleArmy);
    globalProperties.addClearScreenListener(e -> removeTurtles());
    globalProperties.setMakeNewTurtlesConsumer(this::conscriptTurtle);
    newTurtleConsumer = (param) -> {
    }; // Fixes headless tests breaking by adding a dummy
  }

  public void conscriptTurtle(TurtleController recruitTurtle) {
    System.out.println("Conscripting turtle");
    turtleRecruits.add(recruitTurtle);
    newTurtleConsumer.accept(new TurtleProperties(recruitTurtle.getTurtle()));

    if (turtleArmy.size() < recruitTurtle.getTurtle().getId()) {
      for (int i = turtleArmy.size(); i < recruitTurtle.getTurtle().getId(); i++) {
        Turtle freshTurtle = new Turtle(i, new GridCoordinates());
        TurtleController freshTurtleController = new TurtleController(freshTurtle,
            globalProperties);
        turtleRecruits.add(freshTurtleController);
        newTurtleConsumer.accept(new TurtleProperties(freshTurtleController.getTurtle()));
      }
    }

  }

  public void conscriptTurtle(int id) {
    System.out.println("Conscripting turtle");
    turtleRecruits.clear();
    if (turtleArmy.size() < id) {
      for (int i = turtleArmy.size() + 1; i <= id; i++) {
        Turtle freshTurtle = new Turtle(i, new GridCoordinates());
        TurtleController freshTurtleController = new TurtleController(freshTurtle,
            globalProperties);
        turtleRecruits.add(freshTurtleController);
        newTurtleConsumer.accept(new TurtleProperties(freshTurtleController.getTurtle()));
      }
    }
  }

  public void updateTurtleArmy() {
    System.out.println("Recruiting Class Size: " + turtleRecruits.size());
    turtleArmy.addAll(turtleRecruits);
    turtleRecruits.clear();
    System.out.println("Army Size: " + turtleArmy.size());
    globalProperties.setNumTurtlesCreated(turtleArmy.size());
  }

  public Palette getPalette() {
    return palette;
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

  public void createFirstTurtle() {
    conscriptTurtle(1);
    updateTurtleArmy();
    globalProperties.addActiveTurtleId(1);
  }

  private void removeTurtles() {
    //TODO: remove all but one turtles
  }

}
