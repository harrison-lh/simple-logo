package slogo.view.info;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Entry in the turtle shapes palette.
 *
 * @author David Li
 */
public class TurtleShapeEntry extends HBox {

  private final Label indexLabel;
  private final Label shapeLabel;

  /**
   * Main constructor
   * @param index Index of the entry in the shapes palette
   * @param shape The turtle shape
   */
  public TurtleShapeEntry(int index, String shape) {
    indexLabel = new Label(String.valueOf(index));
    shapeLabel = new Label(shape);
    this.setSpacing(20);
    this.getChildren().addAll(indexLabel, shapeLabel);
  }
}
