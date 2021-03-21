package slogo.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class GlobalProperties {

  private final ObjectProperty<Color> backgroundColorProperty;
  private final ObjectProperty<Color> penColorProperty;
  private final DoubleProperty penSizeProperty;
  private final StringProperty turtleShapeProperty;
  private final ListProperty<Color> paletteProperty;

  public GlobalProperties(ListProperty<Color> paletteProperty) {
    backgroundColorProperty = new SimpleObjectProperty<>(Color.WHITE);
    penColorProperty = new SimpleObjectProperty<>(Color.BLACK);
    penSizeProperty = new SimpleDoubleProperty(1);
    turtleShapeProperty = new SimpleStringProperty("Default");
    this.paletteProperty = paletteProperty;
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

  public void setBackgroundColor(Color color) {
    backgroundColorProperty.set(color);
  }
}
