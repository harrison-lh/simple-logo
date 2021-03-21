package slogo.view.info;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorPalatteEntry extends HBox {

  private final Label indexLabel;
  private final Rectangle colorDisplay;

  public ColorPalatteEntry(int index, Color color) {
    indexLabel = new Label(String.valueOf(index));
    colorDisplay = new Rectangle(20, 20);
    colorDisplay.setFill(color);

    this.setSpacing(10);
    this.getChildren().addAll(indexLabel, colorDisplay);
  }

}
