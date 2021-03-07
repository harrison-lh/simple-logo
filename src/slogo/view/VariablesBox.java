package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class VariablesBox extends ScrollPane {

  private Group myContents;

  public VariablesBox() {
    this.setId("VariablesBox");
    this.getStyleClass().add("box");
    myContents = new Group();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("variables"));
  }

}
