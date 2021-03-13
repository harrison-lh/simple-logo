package slogo.model;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

public class ModelPen extends Pen {

  private Paint color;

  public ModelPen(Paint color, ImageView image){
    this.color = color;
  }

  public ModelPen(Paint color){
    this.color = color;
  }

  public Paint getColor() {
    return color;
  }

  public void setColor(Paint color) {
    this.color = color;
  }

  @Override
  public void stamp(double x, double y, double heading) {

  }
}
