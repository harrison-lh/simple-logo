package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import slogo.view.SelectorTarget;

public class GridLines extends Pane {

  private final Line xAxis;
  private final Line yAxis;

  public GridLines() {
    this.setId("GridLines");

    xAxis = new Line();
    xAxis.setStartX(0);
    xAxis.startYProperty().bind(this.heightProperty().divide(2));
    xAxis.endXProperty().bind(this.widthProperty());
    xAxis.endYProperty().bind(this.heightProperty().divide(2));
    xAxis.getStyleClass().add("axis");

    yAxis = new Line();
    yAxis.startXProperty().bind(this.widthProperty().divide(2));
    yAxis.setStartY(0);
    yAxis.endXProperty().bind(this.widthProperty().divide(2));
    yAxis.endYProperty().bind(this.heightProperty());
    yAxis.getStyleClass().add("axis");

    changeGridType("None");

    this.getChildren().addAll(xAxis, yAxis);
  }

  public void changeGridType(String gridType) {
    if (gridType.equals("None")) {
      xAxis.setOpacity(0);
      yAxis.setOpacity(0);
    }
    else if (gridType.equals("Axis")) {
      xAxis.setOpacity(1);
      yAxis.setOpacity(1);
    }
  }
}
