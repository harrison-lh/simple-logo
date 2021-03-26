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
  private final GlobalProperties globalProperties;
  private final Palette palette;
  private Consumer<TurtleProperties> newTurtleConsumer;

  /**
   * The, pardon the tautology, general, constructor for the TurtleGeneral.
   *
   * This constructor instantiates the turtleArmy and turtleRecruits as empty ArrayLists,
   * the Palette as the default color Palette, the variables as an empty set of Variables, and then
   * populates the basic globalProperties.
   */
  public TurtleGeneral() {
    this.turtleArmy = new ArrayList<>();
    palette = new Palette();
    turtleRecruits = new ArrayList<>();
    Variables variables = new Variables();
    globalProperties = new GlobalProperties(palette.getColorsProperty(), variables,
        turtleArmy);
    globalProperties.addClearScreenListener(e -> removeTurtles());
    globalProperties.setMakeNewTurtlesConsumer(this::conscriptTurtle);
    newTurtleConsumer = (param) -> {
    }; // Fixes headless tests breaking by adding a dummy
  }

  /**
   * Adds a new TurtleController to the TurtleRecruits, creating any missing TurtleControllers along
   * the way.
   *
   * NOTE: This particular implementation is only used for testing purposes. Use the overloaded id
   * method in production code to avoid passing around state!!!
   *
   * Ex.) The user inputs TELL [ 3 ] but there's only a single Turtle on screen. conscriptTurtle
   * will create and integrate the missing Turtle with ID 2, as well as the requested Turtle with
   * ID 3.
   * @param recruitTurtle The TurtleController to conscript into the turtleRecruits
   */
  public void conscriptTurtle(TurtleController recruitTurtle) {
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

  /**
   * Adds a new TurtleController with the requested id to the turtleRecruits, creating any missing
   * TurtleControllers along the way.
   *
   * Ex.) The user inputs TELL [ 3 ] but there's only a single Turtle on screen. conscriptTurtle
   * will create and integrate the missing Turtle with ID 2, as well as the requested Turtle with
   * ID 3.
   *
   * @param id The id of the Turtle to conscript into the turtleRecruits.
   */
  public void conscriptTurtle(int id) {
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

  /**
   * Migrates all of the TurtleControllers from the turtleRecruits into the proper turtleArmy,
   * clearing out the turtleRecruits after the migration and updating the globalProperties to
   * properly reflect the number of Turtles created.
   */
  public void updateTurtleArmy() {
    turtleArmy.addAll(turtleRecruits);
    turtleRecruits.clear();
    globalProperties.setNumTurtlesCreated(turtleArmy.size());
  }

  /**
   * Gets the List of TurtleControllers, turtleArmy, associated with this TurtleGeneral.
   *
   * @return The turtleArmy.
   */
  public List<TurtleController> getTurtleArmy() {
    return turtleArmy;
  }

  /**
   * Implements the bindings for the ConsumerInterface for the TurtleGeneral.
   *
   * @param newTurtleConsumer A Consumer of TurtleProperties.
   */
  public void setNewTurtleConsumer(Consumer<TurtleProperties> newTurtleConsumer) {
    this.newTurtleConsumer = newTurtleConsumer;
  }

  /**
   * Gets the GlobalProperties associated with this TurtleGeneral.
   *
   * @return This TurtleGeneral's globalProperties.
   */
  public GlobalProperties getGlobalProperties() {
    return globalProperties;
  }

  /**
   * Bootstraps this TurtleGeneral with a single turtle, id 1, and updates the globalProperties
   * accordingly.
   */
  public void createFirstTurtle() {
    conscriptTurtle(1);
    updateTurtleArmy();
    globalProperties.addActiveTurtleId(1);
  }

  private void removeTurtles() {
    //TODO: remove all but one turtles
  }

}
