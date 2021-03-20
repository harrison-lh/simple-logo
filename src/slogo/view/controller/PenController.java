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

public class PenController extends VBox implements LanguageConsumer {

  private final TextField myInputArea;
  private final Button myPenUpButton;
  private final Button myPenDownButton;
  private final Button myPenThicknessButton;
  private String language = Controller.DEFAULT_LANGUAGE;

  public PenController() {
    this.setSpacing(4);

    myInputArea = new TextField();
    myPenUpButton = new Button("PU");
    myPenDownButton = new Button("PD");
    myPenThicknessButton = new Button("Thickness");
    GridPane buttons = new GridPane();
    buttons.add(myPenUpButton, 0, 0);
    buttons.add(myPenDownButton, 1, 0);
    ColumnConstraints column1 = new ColumnConstraints();
    column1.setPercentWidth(50);
    ColumnConstraints column2 = new ColumnConstraints();
    column2.setPercentWidth(50);
    buttons.getColumnConstraints().addAll(column1, column2);
    this.getChildren().addAll(buttons, myInputArea, myPenThicknessButton);
  }

  public void setExecuteCommandActions(Consumer<String> response) {
    myPenUpButton.setOnAction(e -> executePenToggleCommand(response, translateCommand("PenUp")));
    myPenDownButton.setOnAction(e -> executePenToggleCommand(response, translateCommand("PenDown")));
    myPenThicknessButton.setOnAction(e -> executeThicknessCommand(response));
  }

  @Override
  public Consumer<String> languageConsumer() {
    return newLanguage -> language = newLanguage;
  }

  private String translateCommand(String command) {
    ResourceBundle langResources = ResourceBundle.getBundle(Lexer.RESOURCES_PACKAGE + language);
    String translatedCommand = langResources.getString(command);
    return translatedCommand.substring(0, translatedCommand.indexOf('|'));
  }

  private void executePenToggleCommand(Consumer<String> response, String command) {
    response.accept(command);
  }

  private void executeThicknessCommand(Consumer<String> response) {
    try {
      double distance = Double.parseDouble(myInputArea.getText());
      response.accept(translateCommand("SetPenSize") + " " + distance);
    } catch (NumberFormatException exception) {
      Alert alert = new Alert(AlertType.ERROR, "Invalid input");
      alert.showAndWait();
    }
  }
}
