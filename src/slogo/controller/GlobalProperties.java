package slogo.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

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
  private Collection<EventHandler<ClearScreenEvent>> clearScreenListeners;
  private Consumer<Integer> makeNewTurtlesConsumer;

  public GlobalProperties(ListProperty<Color> paletteProperty) {
    backgroundColorProperty = new SimpleObjectProperty<>(DEFAULT_BACKGROUND_COLOR);
    penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
    penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
    turtleShapeProperty = new SimpleStringProperty(DEFAULT_TURTLE_SHAPE);
    this.paletteProperty = paletteProperty;
    clearScreenListeners = new HashSet<>();
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

  public void addClearScreenListener(EventHandler<ClearScreenEvent> handler) {
    clearScreenListeners.add(handler);
  }

  public void clearScreen() {
    clearScreenListeners.forEach(listener -> listener.handle(new ClearScreenEvent()));
  }

  public void setMakeNewTurtlesConsumer(Consumer<Integer> consumer) {
    makeNewTurtlesConsumer = consumer;
  }

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

}
