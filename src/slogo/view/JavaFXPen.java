package slogo.view;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import slogo.model.Pen;

public class JavaFXPen extends Pen {

  private Paint color;
  private ImageView image;
  public JavaFXPen(Paint color, ImageView image){
    this.color = color;
    this.image = image;
  }

  @Override
  public void stamp(double x, double y, double heading) {
    // TODO: place image on screen at parameter locations
  }
}
