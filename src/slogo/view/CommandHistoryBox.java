package slogo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CommandHistoryBox extends VBox {

  public CommandHistoryBox() {
    this.setId("CommandHistoryBox");
    this.getStyleClass().add("box");
    this.getChildren().add(new Label("command history"));
  }
}
