package slogo.view.canvas;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class PenLine extends Line {

  private double startX;
  private double startY;
  private double endX;
  private double endY;

  public PenLine(double startX, double startY, double endX, double endY, Paint penColor) {
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;

    this.setStroke(penColor);
  }

  public double getStartXCoordinate() {
    return startX;
  }

  public double getStartYCoordinate() {
    return startY;
  }

  public double getEndXCoordinate() {
    return endX;
  }

  public double getEndYCoordinate() {
    return endY;
  }
}
