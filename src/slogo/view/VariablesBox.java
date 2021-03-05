package slogo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VariablesBox extends VBox {

  public VariablesBox() {
    this.setId("VariablesBox");
    this.getStyleClass().add("box");
    this.getChildren().add(new Label("variables"));
  }

}
