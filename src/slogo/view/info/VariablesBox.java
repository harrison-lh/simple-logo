package slogo.view.info;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.controller.Controller;
import slogo.model.Variable;
import slogo.view.LanguageConsumer;

/**
 * Display box for variables
 *
 * @author David Li
 */
public class VariablesBox extends ScrollPane implements LanguageConsumer {

  private final VBox myContents;
  private final List<String> myVariableNames;
  private final List<VariableEntry> myVariableEntries;
  private Consumer<String> myConsumer;
  private String language = Controller.DEFAULT_LANGUAGE;

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

  /**
   * Sets the consumer of command actions
   */
  public void setExecuteCommandAction(Consumer<String> response) {
    myConsumer = response;
  }

  /**
   * Changes this language and the language of each variable entry
   */
  @Override
  public Consumer<String> languageConsumer() {
    return newLanguage -> {
      this.language = newLanguage;
      myVariableEntries.forEach(variableEntry -> variableEntry.setLanguage(newLanguage));
    };
  }

  private void updateVariable(Variable variable) {
    myVariableEntries.get(myVariableNames.indexOf(variable.getName())).updateVariable(variable.getValue());
  }

  private void addVariable(Variable variable) {
    myVariableNames.add(variable.getName());
    VariableEntry variableEntry = new VariableEntry(variable.getName(), variable.getValue(), myConsumer, language);
    variableEntry.getRectangle().widthProperty().bind(this.widthProperty());
    myVariableEntries.add(variableEntry);
    myContents.getChildren().add(variableEntry);
  }
}
