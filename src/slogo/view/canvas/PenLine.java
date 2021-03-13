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

  private final double startX;
  private final double startY;
  private final double endX;
  private final double endY;

  /**
   * Main constructor
   */
  public PenLine(double startX, double startY, double endX, double endY, Paint penColor) {
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;

    this.setStroke(penColor);
  }

  /**
   * @return Relative position of the start x coordinate
   */
  public double getStartXCoordinate() {
    return startX;
  }

  /**
   * @return Relative position of the start y coordinate
   */
  public double getStartYCoordinate() {
    return startY;
  }

  /**
   * @return Relative position of the end x coordinate
   */
  public double getEndXCoordinate() {
    return endX;
  }

  /**
   * @return Relative position of the end y coordinate
   */
  public double getEndYCoordinate() {
    return endY;
  }
}
