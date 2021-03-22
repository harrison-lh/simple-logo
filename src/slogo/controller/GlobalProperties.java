package slogo.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import slogo.controller.commands.UserCommand;

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
  private final Map<Integer, String> shapeMap;
  private final List<UserCommand> userCommands;
  private PropertyChangeListener commandsListener;

  public GlobalProperties(ListProperty<Color> paletteProperty) {
    backgroundColorProperty = new SimpleObjectProperty<>(DEFAULT_BACKGROUND_COLOR);
    penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
    penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
    turtleShapeProperty = new SimpleStringProperty(DEFAULT_TURTLE_SHAPE);
    this.paletteProperty = paletteProperty;
    shapeMap = new HashMap<>();
    shapeMap.put(0, "Default");
    shapeMap.put(1, "Realistic");
    userCommands = new ArrayList<>();

  }

  public void setCommandsListener(PropertyChangeListener commandsListener){
    this.commandsListener = commandsListener;
  }

  /**
   * Removes a user-defined command from the list of userCommands.
   *
   * @param name The name of the command to remove.
   */
  public void deleteUserCommand(String name) {
    userCommands.removeIf(command -> command.getName().equals(name));
  }

  /**
   * Adds a user-defined command to the list of userCommands.
   *
   * @param command The command to add to the list
   */
  public void addUserCommand(UserCommand command) {
    if (containsUserCommand(command.getName())) {
      commandsListener
          .propertyChange(
              new PropertyChangeEvent(this, "UPDATE", command.getName(), command.getName()));
      getUserCommand(command.getName()).updateCommand(command);
    } else {
      commandsListener
          .propertyChange(
              new PropertyChangeEvent(this, "ADD", command.getName(), command.getName()));
      userCommands.add(command);
    }
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
}
