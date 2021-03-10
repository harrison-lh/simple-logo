package slogo.view;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CommandHistoryBox extends ScrollPane {

  private final VBox myContents;
  private Deque<String> pastCommands;

  public CommandHistoryBox() {
    this.setId("CommandHistoryBox");
    myContents = new VBox();
    this.setPrefHeight(InputBox.BOTTOM_HEIGHT);
    this.setFitToWidth(true);

    pastCommands = new LinkedList<>();

    this.setContent(myContents);
  }

  public Deque<String> getPastCommands() {
    return pastCommands;
  }

  public void addCommand(String command) {
    pastCommands.add(command);
    myContents.getChildren().add(new Label("> " + command));
  }
}
