package slogo.view.controller;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import slogo.controller.Controller;
import slogo.controller.Lexer;
import slogo.view.LanguageConsumer;

public abstract class ControllerElement extends VBox implements LanguageConsumer {

  private String language = Controller.DEFAULT_LANGUAGE;
  private TextField myInputArea;

  public ControllerElement() {
    this.setSpacing(4);
  }

  public abstract void setExecuteCommandActions(Consumer<String> response);

  @Override
  public Consumer<String> languageConsumer() {
    return newLanguage -> language = newLanguage;
  }

  protected void setInputArea(TextField inputArea) {
    myInputArea = inputArea;
  }

  protected void executeRawCommand(Consumer<String> response, String command) {
    response.accept(translateCommand(command));
  }

  protected void executeCommandWithInput(Consumer<String> response, String command) {
    try {
      double value = Double.parseDouble(myInputArea.getText());
      response.accept(translateCommand(command) + " " + value);
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
