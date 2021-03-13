package slogo.view;

import java.util.function.Consumer;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.Pen;

/**
 * Pen object used by the view that stores color and whether or not
 * the pen is up or down
 * Prepared to implement stamping of the pen where stamping an image is done
 * instead of drawing a line
 *
 * @author David Li
 */
public class ViewPen extends Pen implements SelectorTarget<Color> {

  private Paint color;

  public ViewPen(Paint color, ImageView image){
    this.color = color;
  }

  public ViewPen(Paint color){
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

  /**
   * Changes color when new pen color is selected
   */
  @Override
  public Consumer<Color> updateAction() {
    return this::setColor;
  }
}
