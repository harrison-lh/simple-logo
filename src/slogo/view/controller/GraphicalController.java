package slogo.view.controller;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GraphicalController extends VBox {

  private static final double WIDTH = 100;

  public GraphicalController() {
    this.setId("GraphicalController");
    this.setPrefWidth(WIDTH);

    this.getChildren().add(new Label("Graphical"));
    this.getChildren().add(new Label("Controller"));
  }

}
