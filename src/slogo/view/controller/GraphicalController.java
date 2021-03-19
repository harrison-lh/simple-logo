package slogo.view.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;
import javafx.scene.layout.VBox;
import slogo.view.LanguageConsumer;

public class GraphicalController extends VBox {

  private static final double WIDTH = 100;
  private final MovementController myMovementController;
  private final RotationController myRotationController;

  public GraphicalController() {
    this.setId("GraphicalController");
    this.setPrefWidth(WIDTH);
    this.setSpacing(20);
    myMovementController = new MovementController();
    myRotationController = new RotationController();
    this.getChildren().addAll(myMovementController, myRotationController);
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myMovementController.setExecuteCommandActions(response);
    myRotationController.setExecuteCommandActions(response);
  }

  public LanguageConsumer[] getLanguageConsumers() {
    return new LanguageConsumer[]{myMovementController, myRotationController};
  }

}
