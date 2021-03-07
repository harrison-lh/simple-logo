package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class CommandHistoryBox extends ScrollPane {

  private Group myContents;

  public CommandHistoryBox() {
    this.setId("CommandHistoryBox");
    this.getStyleClass().add("box");
    myContents = new Group();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("command history"));
  }
}
