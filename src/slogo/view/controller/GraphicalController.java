package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.view.LanguageConsumer;

/**
 * Sidebar containing controllers that allow user to graphically make commands, like moving and
 * rotating the turtle
 *
 * @author David Li
 */
public class GraphicalController extends VBox {

  private static final double WIDTH = 100;
  private final MovementController myMovementController;
  private final RotationController myRotationController;
  private final PenController myPenController;
  private final ClearController myClearController;

  /**
   * Main constructor
   */
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

  /**
   * Sets the execute command action for each internal controller
   *
   * @param response Consumer (which is the MainView) that takes a command as an input and passes
   *                 them to the parser
   */
  public void setExecuteCommandAction(Consumer<String> response) {
    myMovementController.setExecuteCommandActions(response);
    myRotationController.setExecuteCommandActions(response);
    myPenController.setExecuteCommandActions(response);
    myClearController.setExecuteCommandActions(response);
  }

  /**
   * @return Language consumers that need to be notified when the language changes
   */
  public LanguageConsumer[] getLanguageConsumers() {
    return new LanguageConsumer[]{myMovementController, myRotationController, myPenController,
        myClearController};
  }

}
