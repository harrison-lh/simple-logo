package slogo.view.info;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TurtleShapeEntry extends HBox {

  private final Label indexLabel;
  private final Label shapeLabel;

  public TurtleShapeEntry(int index, String shape) {
    indexLabel = new Label(String.valueOf(index));
    shapeLabel = new Label(shape);
    this.setSpacing(20);
    this.getChildren().addAll(indexLabel, shapeLabel);
  }
}
