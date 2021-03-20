package slogo.view;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;
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
  private Consumer<String> myConsumer;

  /**
   * Main constructor
   */
  public CommandHistoryBox() {
    this.setId("CommandHistoryBox");
    myContents = new VBox();
    myContents.heightProperty().addListener((obs, oldV, newV) -> this.setVvalue(1));
    this.setPrefHeight(InputBox.BOTTOM_HEIGHT);
    this.setFitToWidth(true);
    this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    pastCommands = new LinkedList<>();

    this.setContent(myContents);
  }

  /**
   * Add a command to the command history
   *
   * @param command Command to be added
   */
  public void addCommand(String command) {
    pastCommands.add(command);
    CommandHistoryEntry entry = new CommandHistoryEntry(command, myConsumer);
    entry.getRectangle().widthProperty().bind(this.widthProperty());
    myContents.getChildren().add(entry);
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myConsumer = response;
  }
}
