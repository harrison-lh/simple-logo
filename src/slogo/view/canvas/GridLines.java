package slogo.view.canvas;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Holds axes of the grid. Maybe add more gridlines in the future
 *
 * @author David Li
 */
public class GridLines extends Pane {

  private final Line xAxis;
  private final Line yAxis;
  private boolean axesAreVisible;

  /**
   * Main constructor
   */
  public GridLines() {
    this.setId("GridLines");

    xAxis = new Line();
    xAxis.getStyleClass().add("axis");

    yAxis = new Line();
    yAxis.getStyleClass().add("axis");

    xAxis.setStartX(0);
    xAxis.setStartY(TurtleCanvas.DEFAULT_CANVAS_HEIGHT / 2);
    xAxis.setEndX(TurtleCanvas.DEFAULT_CANVAS_WIDTH);
    xAxis.setEndY(TurtleCanvas.DEFAULT_CANVAS_HEIGHT / 2);

    yAxis.setStartX(TurtleCanvas.DEFAULT_CANVAS_WIDTH / 2);
    yAxis.setStartY(0);
    yAxis.setEndX(TurtleCanvas.DEFAULT_CANVAS_WIDTH / 2);
    yAxis.setEndY(TurtleCanvas.DEFAULT_CANVAS_HEIGHT);

    this.getChildren().addAll(xAxis, yAxis);
  }

  /**
   * @param gridType New grid type (none, axis)
   */
  public void changeGridType(String gridType) {
    if (gridType.equals("None")) {
      xAxis.setOpacity(0);
      yAxis.setOpacity(0);
      axesAreVisible = false;
    } else if (gridType.equals("Axis")) {
      xAxis.setOpacity(1);
      yAxis.setOpacity(1);
      axesAreVisible = true;
    }
  }

  public boolean axesAreVisible() {
    return axesAreVisible;
  }

}
