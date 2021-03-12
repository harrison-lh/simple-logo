package slogo.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.model.Variable;

/**
 * Display box for variables
 */
public class VariablesBox extends ScrollPane implements PropertyChangeListener {

  private final VBox myContents;
  private final List<String> myVariableNamesList;
  private final List<Label> myVariableTextList;

  /**
   * Main constructor
   */
  public VariablesBox() {
    this.setId("VariablesBox");
    myContents = new VBox();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("Variables"));
    myVariableNamesList = new ArrayList<>();
    myVariableTextList = new ArrayList<>();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Variable variable = (Variable) evt.getNewValue();
    if (evt.getPropertyName().equals("ADD")) {
      myVariableNamesList.add(variable.getName());
      Label variableText = new Label(variable.toString());
      myVariableTextList.add(variableText);
      myContents.getChildren().add(variableText);
    } else if (evt.getPropertyName().equals("UPDATE")) {
      myVariableTextList.get(myVariableNamesList.indexOf(variable.getName())).setText(variable.toString());
    }
  }
}
