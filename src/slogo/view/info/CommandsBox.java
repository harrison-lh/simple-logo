package slogo.view.info;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Display box of user defined commands For now, only displays the command name
 *
 * @author David Li
 */
public class CommandsBox extends ScrollPane implements PropertyChangeListener {

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

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String command = (String) evt.getNewValue();
    if (evt.getPropertyName().equals("ADD")) {
      addCommand(command);
    } else if (evt.getPropertyName().equals("UPDATE")) {
      updateCommand(command);
    }
  }

  public void setExecuteCommandAction(Consumer<String> response) {
    myConsumer = response;
  }

  private void updateCommand(String command) {

  }

  private void addCommand(String command) {
    UserCommandEntry userCommandEntry = new UserCommandEntry(command, myConsumer);
    userCommandEntry.getRectangle().widthProperty().bind(this.widthProperty());
    myUserCommandEntries.add(userCommandEntry);
    myContents.getChildren().add(userCommandEntry);
  }
}
