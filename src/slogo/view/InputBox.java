package slogo.view;

import javafx.scene.control.TextArea;

public class InputBox extends TextArea {

  public InputBox() {
    this.setId("InputBox");
    this.getStyleClass().add("box");
  }
}
