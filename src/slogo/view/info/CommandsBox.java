package slogo.view.info;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.controller.commands.UserCommand;

/**
 * Display box of user defined commands For now, only displays the command name
 *
 * @author David Li
 */
public class CommandsBox extends ScrollPane {

  private final VBox myContents;
  private final List<UserCommandEntry> myUserCommandEntries;
  private Consumer<String> myConsumer;

  /**
   * Main constructor
   */
  public CommandsBox() {
    this.setId("CommandsBox");
    myContents = new VBox();
    this.setContent(myContents);
    this.getStyleClass().add("info-box");
    this.setFitToWidth(true);
    this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    myUserCommandEntries = new ArrayList<>();
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myConsumer = response;
  }

  public void setCommands(ObservableList<UserCommand> newValue) {
    for (int i = 0; i < newValue.size(); i++) {
      if (i >= myUserCommandEntries.size()) {
        addCommand(newValue.get(i));
      }
      else {
        updateCommand(newValue.get(i));
      }
    }
  }

  private void updateCommand(UserCommand command) {

  }

  private void addCommand(UserCommand command) {
    UserCommandEntry userCommandEntry = new UserCommandEntry(command.getName(), command.getParameters(), myConsumer);
    userCommandEntry.getRectangle().widthProperty().bind(this.widthProperty());
    myUserCommandEntries.add(userCommandEntry);
    myContents.getChildren().add(userCommandEntry);
  }
}
