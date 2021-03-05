package slogo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class UDCommandsBox extends VBox {

  public UDCommandsBox() {
    this.setId("UDCommandsBox");
    this.getStyleClass().add("box");
    this.getChildren().add(new Label("user defined commands"));

  }
}
