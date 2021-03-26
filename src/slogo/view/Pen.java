package slogo.view;

import java.util.function.Consumer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.controller.GlobalProperties;

/**
 * Pen class that stores the pen color and size
 */
public class Pen implements SelectorTarget<Color> {

  private Color color = GlobalProperties.DEFAULT_PEN_COLOR;
  private double size = GlobalProperties.DEFAULT_PEN_SIZE;

  public Paint getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  /**
   * Changes color when new pen color is selected
   */
  public Consumer<Color> updateAction() {
    return this::setColor;
  }

  // public abstract void stamp(double x, double y, double heading);
}
