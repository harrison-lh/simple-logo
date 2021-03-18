package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.layout.VBox;

public class GraphicalController extends VBox {

  private static final double WIDTH = 100;
  private final MovementController myMovementController;

  public GraphicalController() {
    this.setId("GraphicalController");
    this.setPrefWidth(WIDTH);
    myMovementController = new MovementController();
    this.getChildren().add(myMovementController);
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myMovementController.setExecuteCommandActions(response);
  }

}
