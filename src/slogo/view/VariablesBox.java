package slogo.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

  /**
   * Main constructor
   */
  public VariablesBox() {
    this.setId("VariablesBox");
    myContents = new VBox();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("Variables"));
  }

  // TODO: Implement adding and updating variables in variables box
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("ADD")) {
      myContents.getChildren().add(new VariableText((Variable) evt.getNewValue()));
    } else if (evt.getPropertyName().equals("UPDATE")) {
      System.out.println(evt.getNewValue());
    }
  }
}
