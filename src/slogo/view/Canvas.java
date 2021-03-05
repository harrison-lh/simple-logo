package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;

public class Canvas extends Group {

  public Canvas() {
    this.setId("Canvas");
    this.getChildren().add(new Label("canvas"));
  }
}
