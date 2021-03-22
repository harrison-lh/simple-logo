package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import slogo.view.LanguageConsumer;

/**
 * Pen up, pen down, and pen size controls.
 *
 * @authod David Li
 */
public class PenController extends ControllerElement implements LanguageConsumer {

  private final Button myPenUpButton;
  private final Button myPenDownButton;
  private final Button myPenSizeButton;

  public PenController() {
    super();
    this.setId("PenController");
    TextField inputArea = new TextField();
    this.setInputArea(inputArea);
    myPenUpButton = new Button("PU");
    myPenDownButton = new Button("PD");
    myPenSizeButton = new Button("Pen Size");
    inputArea.setId("PenInput");
    myPenUpButton.setId("ControllerPenUpButton");
    myPenDownButton.setId("ControllerPenDownButton");
    myPenSizeButton.setId("ControllerPenSizeButton");
    GridPane buttons = new GridPane();
    buttons.add(myPenUpButton, 0, 0);
    buttons.add(myPenDownButton, 1, 0);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(50);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(50);
    buttons.getColumnConstraints().addAll(column1, column2);
    this.getChildren().addAll(inputArea, myPenSizeButton, buttons);
  }

  public void setExecuteCommandActions(Consumer<String> response) {
    myPenUpButton.setOnAction(e -> executeRawCommand(response, "PenUp"));
    myPenDownButton.setOnAction(e -> executeRawCommand(response, "PenDown"));
    myPenSizeButton.setOnAction(e -> executeCommandWithInput(response, "SetPenSize"));
  }
}
