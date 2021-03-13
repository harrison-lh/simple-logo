package slogo.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Pen {

  public static final Paint DEFAULT_COLOR = Color.BLACK;

  private boolean isActive = true;
  private Paint color = DEFAULT_COLOR;

  public void liftPen(){
    isActive = false;
  }

  public void placePen(){
    isActive = true;
  }

  public boolean isPenActive(){
    return isActive;
  }

  public Paint getColor() {
    return color;
  }

  public void setColor(Paint color) {
    this.color = color;
  }

  // public abstract void stamp(double x, double y, double heading);
}
