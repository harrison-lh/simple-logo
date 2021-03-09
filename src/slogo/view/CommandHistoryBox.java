package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class CommandHistoryBox extends ScrollPane {

  private Pane myContents;

  public CommandHistoryBox() {
    this.setId("CommandHistoryBox");
    myContents = new Pane();
    this.setContent(myContents);
    myContents.getChildren().add(new Label("command history"));
  }
}
