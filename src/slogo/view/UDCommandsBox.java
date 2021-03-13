package slogo.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Display box of user defined commands For now, only displays the command name
 *
 * @author David Li
 */
public class UDCommandsBox extends ScrollPane implements PropertyChangeListener {

  private final VBox myContents;
  private final List<String> myUDCommandsNamesList;
  private final List<Label> myUDCommandsTextList;

  /**
   * Main constructor
   */
  public UDCommandsBox() {
    this.setId("UDCommandsBox");
    myContents = new VBox();
    this.setContent(myContents);
    Label title = new Label("User-Defined Commands");
    title.getStyleClass().add("box-title");
    myContents.getChildren().add(title);
    myUDCommandsNamesList = new ArrayList<>();
    myUDCommandsTextList = new ArrayList<>();
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

  private void updateCommand(String command) {
    myUDCommandsTextList.get(myUDCommandsNamesList.indexOf(command)).setText(command);
  }

  private void addCommand(String command) {
    myUDCommandsNamesList.add(command);
    Label commandText = new Label(command);
    myUDCommandsTextList.add(commandText);
    myContents.getChildren().add(commandText);
  }
}
