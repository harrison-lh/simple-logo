package slogo.view.info;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import slogo.controller.Controller;
import slogo.controller.Lexer;
import slogo.view.ClickableEntry;

/**
 * Entry in the variables box of the info display
 */
public class VariableEntry extends ClickableEntry<String> {

  private final String myName;
  private double myValue;
  private String language;

  /**
   * Main constructor
   * @param name Variable name
   * @param value Variable value
   * @param consumer Consumer of change variable command
   * @param language Current language
   */
  public VariableEntry(String name, double value, Consumer<String> consumer, String language) {
    super(consumer);
    myName = name;
    myValue = value;
    this.language = language;
    setText(displayText());
  }

  public void updateVariable(double value) {
    myValue = value;
    setText(displayText());
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  protected void onClick(Consumer<String> consumer) {
    TextInputDialog inputDialog = new TextInputDialog();
    inputDialog.setHeaderText(null);
    inputDialog.setGraphic(null);
    inputDialog.setContentText(myName.substring(1) + " =");
    inputDialog.showAndWait().ifPresent(result -> {
      try {
        double newValue = Double.parseDouble(result);
        consumer.accept(setVariableCommand(newValue));
      } catch(NumberFormatException exception) {
        Alert alert = new Alert(AlertType.ERROR, "Invalid input");
        alert.showAndWait();
      }
    });
  }

  private String setVariableCommand(double newValue) {
    return translateCommand("MakeVariable") + " " + myName + " " + newValue;
  }

  private String displayText() {
    return myName.substring(1) + " = " + myValue; // Remove colon
  }

  private String translateCommand(String command) {
    ResourceBundle langResources = ResourceBundle.getBundle(Lexer.RESOURCES_PACKAGE + language);
    String translatedCommand = langResources.getString(command);
    return translatedCommand.substring(0, translatedCommand.indexOf('|'));
  }

}
