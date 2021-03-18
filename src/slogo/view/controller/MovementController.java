package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MovementController extends VBox {

  private final TextField myInputArea;
  private final Button myForwardButton;
  private final Button myBackButton;

  public MovementController() {
    this.setSpacing(4);

    myInputArea = new TextField();
    myForwardButton = new Button("FD");
    myBackButton = new Button("BK");
    GridPane buttons = new GridPane();
    buttons.add(myForwardButton, 0, 0);
    buttons.add(myBackButton, 1, 0);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(50);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(50);
    buttons.getColumnConstraints().addAll(column1, column2);
    this.getChildren().addAll(myInputArea, buttons);
  }

  public void setExecuteCommandActions(Consumer<String> response) {
    // TODO: retrieve commands in current language
    myForwardButton.setOnAction(e -> executeCommand(response, "fd"));
    myBackButton.setOnAction(e -> executeCommand(response, "bk"));
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
