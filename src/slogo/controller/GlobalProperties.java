package slogo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
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
  private final Map<Integer, String> shapeMap;
  private final Set<Integer> activeTurtleIds;
  private int numTurtlesCreated;
  private List<TurtleController> turtleArmy;

  public GlobalProperties(ListProperty<Color> paletteProperty, List<TurtleController> turtleArmy) {
    backgroundColorProperty = new SimpleObjectProperty<>(DEFAULT_BACKGROUND_COLOR);
    penColorProperty = new SimpleObjectProperty<>(DEFAULT_PEN_COLOR);
    penSizeProperty = new SimpleDoubleProperty(DEFAULT_PEN_SIZE);
    turtleShapeProperty = new SimpleStringProperty(DEFAULT_TURTLE_SHAPE);
    this.paletteProperty = paletteProperty;
    shapeMap = new HashMap<>();
    shapeMap.put(0, "Default");
    shapeMap.put(1, "Realistic");
    clearScreenListeners = new HashSet<>();
    activeTurtleIds = new HashSet<>();
    numTurtlesCreated = 0;
    this.turtleArmy = turtleArmy;
  }

  public List<TurtleController> getCopyOfTurtleArmy(){
    List<TurtleController> turtleArmyCopy = new ArrayList<>();
    for(TurtleController tc : turtleArmy){
      turtleArmyCopy.add(tc);
    }

    return (List<TurtleController>) turtleArmy;
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

  public void setNumTurtlesCreated(int numTurtlesCreated) {
    this.numTurtlesCreated = numTurtlesCreated;
  }

  public int getNumTurtlesCreated() {
    return numTurtlesCreated;
  }
}
