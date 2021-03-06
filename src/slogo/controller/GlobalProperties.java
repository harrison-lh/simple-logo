package slogo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import slogo.controller.commands.UserCommand;
import slogo.model.Variables;

/**
 * Class containing all of the global properties that can be modified using commands
 * or graphically in the view.
 *
 * @author David Li
 * @Author Cole Spector
 */
public class GlobalProperties {

  public static Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
  public static Color DEFAULT_PEN_COLOR = Color.BLACK;
  public static Double DEFAULT_PEN_SIZE = 1.0;
  public static String DEFAULT_TURTLE_SHAPE = "Default";

  private final ObjectProperty<Color> backgroundColorProperty;
  private final ObjectProperty<Color> penColorProperty;
  private final DoubleProperty penSizeProperty;
  private final StringProperty turtleShapeProperty;
  private final ListProperty<Color> paletteProperty;
  private final ClearScreenEvent clearScreenEvent;
  private final Map<Integer, String> shapeMap;
  private final List<UserCommand> userCommands;
  private final Map<String, String> userCommandsMap;
  private final ListProperty<UserCommand> userCommandsProperty;
  private final Set<Integer> activeTurtleIds;
  private final Variables variables;
  private final MapProperty<String, Double> variableMapProperty;
  private final Collection<EventHandler<ClearScreenEvent>> clearScreenListeners;
  private Consumer<Integer> makeNewTurtlesConsumer;
  private int numTurtlesCreated;
  private final List<TurtleController> turtleArmy;

  /**
   * Main constructor
   * @param paletteProperty the palette contents as a ListProperty
   * @param variables Variables stored in the turtle general
   * @param turtleArmy List of turtle controllers
   */
  public GlobalProperties(ListProperty<Color> paletteProperty, Variables variables,
      List<TurtleController> turtleArmy) {
    backgroundColorProperty = new SimpleObjectProperty<>(DEFAULT_BACKGROUND_COLOR);
    penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
    penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
    turtleShapeProperty = new SimpleStringProperty(DEFAULT_TURTLE_SHAPE);
    this.paletteProperty = paletteProperty;
    shapeMap = new HashMap<>();
    shapeMap.put(0, "Default");
    shapeMap.put(1, "Realistic");
    clearScreenListeners = new HashSet<>();
    clearScreenEvent = new ClearScreenEvent(this);
    activeTurtleIds = new HashSet<>();
    numTurtlesCreated = 0;
    this.variables = variables;
    this.variableMapProperty = variables.getMapProperty();
    this.turtleArmy = turtleArmy;
    userCommands = new ArrayList<>();
    userCommandsMap = new HashMap<>();
    userCommandsProperty = new SimpleListProperty<>(
        FXCollections.observableArrayList(userCommands));
  }

  /**
   * Removes a user-defined command from the list of userCommands.
   *
   * @param name The name of the command to remove.
   */
  public void deleteUserCommand(String name) {
    userCommands.removeIf(command -> command.getName().equals(name));
    updateCommandsMap();
    userCommandsProperty.setValue(FXCollections.observableArrayList(userCommands));
  }

  /**
   * Adds a user-defined command to the list of userCommands.
   *
   * @param command The command to add to the list
   */
  public void addUserCommand(UserCommand command) {
    if (containsUserCommand(command.getName())) {
      getUserCommand(command.getName()).updateCommand(command);
    } else {
      userCommands.add(command);
    }
    updateCommandsMap();
    userCommandsProperty.setValue(FXCollections.observableArrayList(userCommands));
  }

  /**
   * Check the list of userCommands to see if it contains the user-command in question, and if so
   * return it!
   *
   * @param name The name of the user-command.
   * @return The UserCommand, if it exists.
   */
  public UserCommand getUserCommand(String name) {
    for (UserCommand command : userCommands) {
      if (command.getName().equals(name)) {
        return command;
      }
    }
    return null;
  }

  public List<UserCommand> getUserCommands() {
    return userCommands;
  }

  /**
   * Check the list of userCommands to see if it contains the user-command in question.
   *
   * @param name The name of the user-command.
   * @return The presence of the user-command.
   */
  public boolean containsUserCommand(String name) {
    for (UserCommand command : userCommands) {
      if (command.getName().equals(name)) {
        return true;
      }
    }
    return false;

  }

  public List<TurtleController> getCopyOfTurtleArmy() {
    List<TurtleController> turtleArmyCopy = new ArrayList<>();
    for (TurtleController tc : turtleArmy) {
      turtleArmyCopy.add(tc);
    }

    return turtleArmyCopy;
  }

  public ObjectProperty<Color> backgroundColorPropertyProperty() {
    return backgroundColorProperty;
  }

  public ObjectProperty<Color> penColorPropertyProperty() {
    return penColorProperty;
  }

  public DoubleProperty penSizePropertyProperty() {
    return penSizeProperty;
  }

  public StringProperty turtleShapePropertyProperty() {
    return turtleShapeProperty;
  }

  public ListProperty<Color> paletteProperty() {
    return paletteProperty;
  }

  public double getVariableValue(String name) {
    return variables.getValue(name);
  }

  public MapProperty<String, Double> variableMapPropertyProperty() {
    return variableMapProperty;
  }

  public ListProperty<UserCommand> userCommandsProperty() {
    return userCommandsProperty;
  }

  public Map<String, Double> getVariablesMap() {
    return variables.getVarMap();
  }

  public boolean containsVariable(String name) {
    return variables.containsKey(name);
  }

  public void setVariableValue(String name, double value) {
    variables.setValue(name, value);
  }

  public void removeVariable(String name) {
    variables.removeValue(name);
  }

  public void addClearScreenListener(EventHandler<ClearScreenEvent> handler) {
    clearScreenListeners.add(handler);
  }

  /**
   * Notifies all the clear screen listeners that the screen is being cleared
   */
  public void clearScreen() {
    clearScreenListeners.forEach(listener -> listener.handle(clearScreenEvent));
  }

  public void setMakeNewTurtlesConsumer(Consumer<Integer> consumer) {
    makeNewTurtlesConsumer = consumer;
  }

  /**
   * Notifies the consumer that a new turtle is being made
   * @param param Id of the new turtle
   */
  public void makeNewTurtles(int param) {
    makeNewTurtlesConsumer.accept(param);
  }

  public void setBackgroundColorProperty(Color backgroundColor) {
    this.backgroundColorProperty.set(backgroundColor);
  }

  public void setPenColorProperty(Color penColor) {
    this.penColorProperty.set(penColor);
  }

  public void setPenSizeProperty(double penSize) {
    this.penSizeProperty.set(penSize);
  }

  public void setTurtleShapeProperty(String turtleShape) {
    this.turtleShapeProperty.set(turtleShape);
  }

  public void setPaletteProperty(int index, Color newColor) {
    this.paletteProperty().set(index, newColor);
  }

  public Map<Integer, String> getShapeMap() {
    return shapeMap;
  }

  public Set<Integer> getActiveTurtleIds() {
    return activeTurtleIds;
  }

  public void addActiveTurtleId(int idToAdd) {
    activeTurtleIds.add(idToAdd);
  }

  public void addMultipleActiveTurtleIds(Collection<Integer> idsToAdd) {
    activeTurtleIds.addAll(idsToAdd);
  }

  public void clearActiveTurtleIds() {
    activeTurtleIds.clear();
  }

  public int getNumTurtlesCreated() {
    return numTurtlesCreated;
  }

  public void setNumTurtlesCreated(int numTurtlesCreated) {
    this.numTurtlesCreated = numTurtlesCreated;
  }

  public Map<String, String> getCommandsMap() {
    return userCommandsMap;
  }

  private void updateCommandsMap() {
    userCommands.forEach(command -> {
      userCommandsMap.put(command.getName(), command.getFullCommand());
    });
  }
}
