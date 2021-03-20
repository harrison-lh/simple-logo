package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import slogo.view.LanguageConsumer;

/**
 * Rotate left and right controls.
 *
 * @author David Li
 */
public class RotationController extends ControllerElement implements LanguageConsumer {

  private final Button myLeftButton;
  private final Button myRightButton;

  public RotationController() {
    super();
    this.setId("RotationController");
    TextField inputArea = new TextField();
    this.setInputArea(inputArea);
    myLeftButton = new Button("LT");
    myRightButton = new Button("RT");
    inputArea.setId("RotationInput");
    myLeftButton.setId("ControllerLeftButton");
    myRightButton.setId("ControllerRightButton");
    GridPane buttons = new GridPane();
    buttons.add(myLeftButton, 0, 0);
    buttons.add(myRightButton, 1, 0);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(50);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(50);
    buttons.getColumnConstraints().addAll(column1, column2);
    this.getChildren().addAll(inputArea, buttons);
  }

  public void setExecuteCommandActions(Consumer<String> response) {
    myLeftButton.setOnAction(e -> executeCommandWithInput(response, "Left"));
    myRightButton.setOnAction(e -> executeCommandWithInput(response, "Right"));
  }
}
