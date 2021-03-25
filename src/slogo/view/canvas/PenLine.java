package slogo.view.canvas;

import javafx.scene.shape.Line;

/**
 * Single line drawn by the pen in the turtle canvas.
 *
 * @author David Li
 */
public class PenLine extends Line {

  /**
   * Main constructor
   * @param startX Cartesian x-coordinate of the start x
   * @param startY Cartesian x-coordinate of the end x
   * @param endX Cartesian y-coordinate of the start y
   * @param endY Cartesian y-coordinate of the end y
   */
  public PenLine(double startX, double startY, double endX, double endY) {
    this.setStartX(TurtleCanvas.convertXCoordinate(startX) + TurtleCanvas.DEFAULT_CANVAS_WIDTH / 2);
    this.setStartY(
        TurtleCanvas.convertYCoordinate(startY) + TurtleCanvas.DEFAULT_CANVAS_HEIGHT / 2);
    this.setEndX(TurtleCanvas.convertXCoordinate(endX) + TurtleCanvas.DEFAULT_CANVAS_WIDTH / 2);
    this.setEndY(TurtleCanvas.convertYCoordinate(endY) + TurtleCanvas.DEFAULT_CANVAS_HEIGHT / 2);
  }
}
