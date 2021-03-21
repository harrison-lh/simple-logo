package slogo.view.info;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import slogo.controller.TurtleProperties;

/**
 * Display box for turtles
 *
 * @author David Li
 */
public class TurtlesBox extends ScrollPane {

  private final VBox myContents;
  /**
   * Main constructor
   */
  public TurtlesBox() {
    this.setId("TurtlesBox");
    this.getStyleClass().add("info-box");
    myContents = new VBox();
    this.setContent(myContents);
  }

  public void addTurtle(TurtleProperties turtleProperties) {
    myContents.getChildren().add(new TurtleEntry(turtleProperties));
  }
}
