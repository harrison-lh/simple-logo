package slogo.view.canvas;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 * Single line drawn by the pen in the turtle canvas. Coordinates based on internal coordinate
 * system
 *
 * @author David Li
 */
public class PenLine extends Line {

  /**
   * Main constructor
   */
  public PenLine(double startX, double startY, double endX, double endY, Paint penColor) {
    this.setStartX(TurtleCanvas.convertXCoordinate(startX) + TurtleCanvas.DEFAULT_CANVAS_WIDTH / 2);
    this.setStartY(TurtleCanvas.convertYCoordinate(startY) + TurtleCanvas.DEFAULT_CANVAS_HEIGHT / 2);
    this.setEndX(TurtleCanvas.convertXCoordinate(endX) + TurtleCanvas.DEFAULT_CANVAS_WIDTH / 2);
    this.setEndY(TurtleCanvas.convertYCoordinate(endY) + TurtleCanvas.DEFAULT_CANVAS_HEIGHT / 2);

    this.setStroke(penColor);
  }
}
