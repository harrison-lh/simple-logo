package slogo.view.info;

import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import slogo.view.ClickableEntry;

public class VariableEntry extends ClickableEntry<String> {

  private final String myName;
  private double myValue;

  public VariableEntry(String name, double value, Consumer<String> consumer) {
    super(consumer);
    myName = name;
    myValue = value;
  }

  public void updateVariable(double value) {
    myValue = value;
    setText(displayText());
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
    return "make " + myName + " " + newValue;
  }

  private String displayText() {
    return myName.substring(1) + " = " + myValue; // Remove colon
  }
}
