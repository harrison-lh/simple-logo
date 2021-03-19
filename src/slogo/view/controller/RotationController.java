package slogo.view.controller;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import slogo.controller.Controller;
import slogo.controller.Lexer;
import slogo.view.LanguageConsumer;

public class RotationController extends VBox implements LanguageConsumer {

  private final TextField myInputArea;
  private final Button myLeftButton;
  private final Button myRightButton;
  private String language = Controller.DEFAULT_LANGUAGE;

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
    myLeftButton.setOnAction(e -> executeCommand(response, translateCommand("Left")));
    myRightButton.setOnAction(e -> executeCommand(response, translateCommand("Right")));
  }

  @Override
  public Consumer<String> languageConsumer() {
    return newLanguage -> language = newLanguage;
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

  private String translateCommand(String command) {
    ResourceBundle langResources = ResourceBundle.getBundle(Lexer.RESOURCES_PACKAGE + language);
    String translatedCommand = langResources.getString(command);
    return translatedCommand.substring(0, translatedCommand.indexOf('|'));
  }

}
