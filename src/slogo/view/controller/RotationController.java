package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RotationController extends VBox {

  private final TextField myInputArea;
  private final Button myLeftButton;
  private final Button myRightButton;

  public RotationController() {
    this.setSpacing(4);

    myInputArea = new TextField();
    myLeftButton = new Button("LT");
    myRightButton = new Button("RT");
    GridPane buttons = new GridPane();
    buttons.add(myLeftButton, 0, 0);
    buttons.add(myRightButton, 1, 0);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(50);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(50);
    buttons.getColumnConstraints().addAll(column1, column2);
    this.getChildren().addAll(myInputArea, buttons);
  }

  public void setExecuteCommandActions(Consumer<String> response) {
    // TODO: retrieve commands in current language
    myLeftButton.setOnAction(e -> executeCommand(response, "lt"));
    myRightButton.setOnAction(e -> executeCommand(response, "rt"));
  }

  private void executeCommand(Consumer<String> response, String command) {
    try {
      double distance = Double.parseDouble(myInputArea.getText());
      response.accept(command + " " + distance);
    } catch (NumberFormatException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid input");
      alert.showAndWait();
    }
  }
}
