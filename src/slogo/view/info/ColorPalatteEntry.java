package slogo.view.info;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A single color entry in the color palette.
 *
 * @author David Li
 */
public class ColorPalatteEntry extends HBox {

  private final Label indexLabel;
  private final Rectangle colorDisplay;

  /**
   * Main constructor
   * @param index The index of the color in the palatte
   * @param color The initial color
   */
  public ColorPalatteEntry(int index, Color color) {
    indexLabel = new Label(String.valueOf(index));
    colorDisplay = new Rectangle(200, 20);
    colorDisplay.setFill(color);

    this.setSpacing(20);
    this.getChildren().addAll(indexLabel, colorDisplay);
    HBox.setHgrow(colorDisplay, Priority.ALWAYS);
  }

  /**
   * Change the color of the entry
   * @param color The new color
   */
  public void setColor(Color color) {
    colorDisplay.setFill(color);
  }
}
