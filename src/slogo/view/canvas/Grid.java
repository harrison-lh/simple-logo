package slogo.view.canvas;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Grid extends Pane {

  public Grid() {
    this.setId("Grid");

    Line xAxis = new Line();
    xAxis.setStartX(0);
    xAxis.startYProperty().bind(this.heightProperty().divide(2));
    xAxis.endXProperty().bind(this.widthProperty());
    xAxis.endYProperty().bind(this.heightProperty().divide(2));
    xAxis.getStyleClass().add("axis");

    Line yAxis = new Line();
    yAxis.startXProperty().bind(this.widthProperty().divide(2));
    yAxis.setStartY(0);
    yAxis.endXProperty().bind(this.widthProperty().divide(2));
    yAxis.endYProperty().bind(this.heightProperty());
    yAxis.getStyleClass().add("axis");

    this.getChildren().addAll(xAxis, yAxis);
  }

  public void changeGridType(String gridType) {

  }
}
