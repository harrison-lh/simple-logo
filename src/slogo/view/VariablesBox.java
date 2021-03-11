package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * Display box for variables
 */
public class VariablesBox extends ScrollPane {

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

}
