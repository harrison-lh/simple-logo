package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import slogo.view.LanguageConsumer;

/**
 * Forward and backward controls.
 *
 * @author David Li
 */
public class MovementController extends ControllerElement implements LanguageConsumer {

  private final Button myForwardButton;
  private final Button myBackwardButton;

  /***
   * Main constructor
   */
  public MovementController() {
    super();
    this.setId("MovementController");
    TextField inputArea = new TextField();
    this.setInputArea(inputArea);
    myForwardButton = new Button("FD");
    myBackwardButton = new Button("BK");
    inputArea.setId("MovementInput");
    myForwardButton.setId("ControllerForwardButton");
    myBackwardButton.setId("ControllerBackwardButton");
    GridPane buttons = new GridPane();
    buttons.add(myForwardButton, 0, 0);
    buttons.add(myBackwardButton, 1, 0);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(50);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(50);
    buttons.getColumnConstraints().addAll(column1, column2);
    this.getChildren().addAll(inputArea, buttons);
  }

  /***
   * Sends the forward command to the consumer when the forward button is pressed, and
   * sends the backward command to the consumer when the backward button is pressed
   * @param response the consumer receiving commands
   */
  public void setExecuteCommandActions(Consumer<String> response) {
    myForwardButton.setOnAction(e -> executeCommandWithInput(response, "Forward"));
    myBackwardButton.setOnAction(e -> executeCommandWithInput(response, "Backward"));
  }
}
