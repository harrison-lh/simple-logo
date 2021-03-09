package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class UDCommandsBox extends ScrollPane {

  private Group myContents;

  public UDCommandsBox() {
    this.setId("UDCommandsBox");
    myContents = new Group();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("user defined commands"));
  }
}
