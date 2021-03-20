package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.view.LanguageConsumer;

public class GraphicalController extends VBox {

  private static final double WIDTH = 100;
  private final MovementController myMovementController;
  private final RotationController myRotationController;
  private final PenController myPenController;
  private final ClearController myClearController;

  public GraphicalController() {
    this.setId("GraphicalController");
    this.setPrefWidth(WIDTH);
    this.setSpacing(20);
    myMovementController = new MovementController();
    myRotationController = new RotationController();
    myPenController = new PenController();
    myClearController = new ClearController();
    this.getChildren().addAll(myMovementController, myRotationController, myPenController);
    Pane spacer = new Pane();
    this.getChildren().add(spacer);
    VBox.setVgrow(spacer, Priority.ALWAYS);
    this.getChildren().add(myClearController);
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myMovementController.setExecuteCommandActions(response);
    myRotationController.setExecuteCommandActions(response);
    myPenController.setExecuteCommandActions(response);
    myClearController.setExecuteCommandActions(response);
  }

  public LanguageConsumer[] getLanguageConsumers() {
    return new LanguageConsumer[]{myMovementController, myRotationController, myPenController,
        myClearController};
  }

}
