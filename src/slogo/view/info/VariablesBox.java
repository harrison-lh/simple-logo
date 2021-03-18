package slogo.view.info;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.model.Variable;

/**
 * Display box for variables
 */
public class VariablesBox extends ScrollPane implements PropertyChangeListener {

  private final VBox myContents;
  private final List<String> myVariableNames;
  private final List<VariableEntry> myVariableEntries;
  private Consumer<String> myConsumer;

  /**
   * Main constructor
   */
  public VariablesBox() {
    this.setId("VariablesBox");
    this.getStyleClass().add("info-box");
    this.setFitToWidth(true);
    this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    myContents = new VBox();
    this.setContent(myContents);
    myVariableNames = new ArrayList<>();
    myVariableEntries = new ArrayList<>();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Variable variable = (Variable) evt.getNewValue();
    if (evt.getPropertyName().equals("ADD")) {
      addVariable(variable);
    } else if (evt.getPropertyName().equals("UPDATE")) {
      updateVariable(variable);
    }
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myConsumer = response;
  }

  private void updateVariable(Variable variable) {
    myVariableEntries.get(myVariableNames.indexOf(variable.getName())).updateVariable(variable.getValue());
  }

  private void addVariable(Variable variable) {
    myVariableNames.add(variable.getName());
    VariableEntry variableEntry = new VariableEntry(variable.getName(), variable.getValue(), myConsumer);
    variableEntry.getRectangle().widthProperty().bind(this.widthProperty());
    myVariableEntries.add(variableEntry);
    myContents.getChildren().add(variableEntry);
  }
}
