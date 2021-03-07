package slogo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Canvas extends StackPane {

  public Canvas() {
    this.setId("Canvas");
    this.getStyleClass().add("box");
    this.getChildren().add(new Label("canvas"));
  }
}
