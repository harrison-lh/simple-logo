package slogo.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * Display box for variables
 */
public class VariablesBox extends ScrollPane implements PropertyChangeListener {

  private Group myContents;

  /**
   * Main constructor
   */
  public VariablesBox() {
    this.setId("VariablesBox");
    myContents = new Group();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("variables"));
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("ADD")) {
      System.out.println("add variable");
    }
    else     if (evt.getPropertyName().equals("UPDATE")) {
      System.out.println("update variable");
    }
  }
}
