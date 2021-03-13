package slogo.view;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Display box for the commands inputted by the user
 *
 * @author David Li
 */
public class CommandHistoryBox extends ScrollPane {

  private final VBox myContents;
  private final Deque<String> pastCommands;

  /**
   * Main constructor
   */
  public CommandHistoryBox() {
    this.setId("CommandHistoryBox");
    myContents = new VBox();
    this.setPrefHeight(InputBox.BOTTOM_HEIGHT);
    this.setFitToWidth(true);

    pastCommands = new LinkedList<>();

    this.setContent(myContents);
  }

  /**
   * @return Deque of all the past commands inputted
   */
  public Deque<String> getPastCommands() {
    return pastCommands;
  }

  /**
   * Add a command to the command history
   * @param command Command to be added
   */
  public void addCommand(String command) {
    String reformattedCommand = "> " + command.replace("\n", "\n  ");
    pastCommands.add(command);
    Label commandLabel = new Label(reformattedCommand);
    commandLabel.getStyleClass().add("monospace-font");
    myContents.getChildren().add(commandLabel);
  }
}
