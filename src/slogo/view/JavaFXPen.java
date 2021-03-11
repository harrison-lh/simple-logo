package slogo.view;

import java.util.function.Consumer;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.Pen;

public class JavaFXPen extends Pen implements SelectorTarget<Color> {

  private Paint color;

  public JavaFXPen(Paint color, ImageView image){
    this.color = color;
  }

  public JavaFXPen(Paint color){
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
    // TODO: place image on screen at parameter locations
  }

  @Override
  public Consumer<Color> updateAction() {
    return this::setColor;
  }
}
