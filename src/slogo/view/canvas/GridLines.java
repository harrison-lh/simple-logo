package slogo.view.canvas;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class GridLines extends Pane {

  private final Line xAxis;
  private final Line yAxis;
  private boolean axisIsVisible;

  public GridLines() {
    this.setId("GridLines");

    xAxis = new Line();
    xAxis.getStyleClass().add("axis");

    yAxis = new Line();
    yAxis.getStyleClass().add("axis");

    changeGridType("None");

    this.getChildren().addAll(xAxis, yAxis);

    this.resize();
  }

  public void changeGridType(String gridType) {
    if (gridType.equals("None")) {
      xAxis.setOpacity(0);
      yAxis.setOpacity(0);
      axisIsVisible = false;
    }
    else if (gridType.equals("Axis")) {
      xAxis.setOpacity(1);
      yAxis.setOpacity(1);
      axisIsVisible = true;
    }
    this.resize();
  }

  public boolean axisIsVisible() {
    return axisIsVisible;
  }

  public void resize() {
    xAxis.setStartX(0);
    xAxis.setStartY(this.getHeight() / 2);
    xAxis.setEndX(this.getWidth());
    xAxis.setEndY(this.getHeight() / 2);

    yAxis.setStartX(this.getWidth() / 2);
    yAxis.setStartY(0);
    yAxis.setEndX(this.getWidth() / 2);
    yAxis.setEndY(this.getHeight());
  }
}
