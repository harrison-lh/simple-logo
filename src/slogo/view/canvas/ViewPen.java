package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.model.Pen;
import slogo.view.SelectorTarget;

/**
 * Pen object used by the view that stores color and whether or not
 * the pen is up or down
 *
 * @author David Li
 */
public class ViewPen extends Pen implements SelectorTarget<Color> {

  public ViewPen(){
    super();
  }

  /**
   * Changes color when new pen color is selected
   */
  @Override
  public Consumer<Color> updateAction() {
    return this::setColor;
  }
}
