package slogo.view;

import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Pen implements SelectorTarget<Color>{

  public static final Paint DEFAULT_COLOR = Color.BLACK;

  private BooleanProperty isActive = new SimpleBooleanProperty(true);
  private Paint color = DEFAULT_COLOR;

  public void liftPen(){
    isActive.setValue(false);
  }

  public void placePen(){
    isActive.setValue(true);
  }

  public boolean isPenActive(){
    return isActive.get();
  }

  public BooleanProperty penActiveProperty() {
    return isActive;
  }

  public Paint getColor() {
    return color;
  }

  public void setColor(Paint color) {
    this.color = color;
  }

  /**
   * Changes color when new pen color is selected
   */
  public Consumer<Color> updateAction() {
    return this::setColor;
  }

  // public abstract void stamp(double x, double y, double heading);
}
