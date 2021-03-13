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

  public ViewPen(Paint color){
    this.color = color;
  }

  /**
   * Changes color when new pen color is selected
   */
  @Override
  public Consumer<Color> updateAction() {
    return this::setColor;
  }
}
