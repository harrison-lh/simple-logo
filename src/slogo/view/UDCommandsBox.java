package slogo.view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * Display box of user defined commands
 *
 * @author David Li
 */
public class UDCommandsBox extends ScrollPane {

  private Group myContents;

  /**
   * Main constructor
   */
  public UDCommandsBox() {
    this.setId("UDCommandsBox");
    myContents = new Group();
    this.setContent(myContents);
    Label title = new Label("User-Defined Commands");
    title.getStyleClass().add("box-title");
    myContents.getChildren().add(title);
  }
}
