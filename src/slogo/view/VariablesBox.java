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

  private VBox myContents;
  private List<Variable> myVariableList;
  private List<Label> myVariableTextList;

  /**
   * Main constructor
   */
  public VariablesBox() {
    this.setId("VariablesBox");
    myContents = new VBox();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("Variables"));
    myVariableList = new ArrayList<>();
    myVariableTextList = new ArrayList<>();
  }

  // TODO: Implement adding and updating variables in variables box
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Variable variable = (Variable) evt.getNewValue();
    if (evt.getPropertyName().equals("ADD")) {
      myVariableList.add(variable);
      Label variableText = new Label(variable.toString());
      myVariableTextList.add(variableText);
      myContents.getChildren().add(variableText);
    } else if (evt.getPropertyName().equals("UPDATE")) {
      myVariableTextList.get(myVariableList.indexOf(variable)).setText(variable.toString());
    }
  }
}
