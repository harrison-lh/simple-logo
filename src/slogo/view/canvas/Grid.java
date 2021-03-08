package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import slogo.view.SelectorTarget;

public class Grid extends Pane implements SelectorTarget<String> {

  private final Line xAxis;
  private final Line yAxis;

  public Grid() {
    this.setId("Grid");

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

  @Override
  public Consumer<String> updateAction() {
    return this::changeGridType;
  }

  private void changeGridType(String gridType) {
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
