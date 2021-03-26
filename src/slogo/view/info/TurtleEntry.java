package slogo.view.info;

import javafx.scene.control.Label;
import slogo.controller.TurtleProperties;

/**
 * Single turtle info display in turtles box of the info display
 *
 * @author David Li
 */
public class TurtleEntry extends Label {

  private final int myId;
  private String coordinates;
  private boolean visible;
  private boolean penActive;

  /**
   * Main constructor
   * @param turtleProperties Properties of the turtle that links to the text
   */
  public TurtleEntry(TurtleProperties turtleProperties) {
    myId = turtleProperties.getId();
    coordinates = turtleProperties.getCoordinates().toString();
    visible = turtleProperties.visibleProperty().getValue();
    turtleProperties.getCoordinates().stringProperty()
        .addListener(((observable, oldValue, newValue) -> {
          this.coordinates = newValue;
          updateString();
        }));
    turtleProperties.visibleProperty().addListener(((observable, oldValue, newValue) -> {
      visible = newValue;
      updateString();
    }));
    turtleProperties.penActiveProperty().addListener(((observable, oldValue, newValue) -> {
      penActive = newValue;
      updateString();
    }));
    penActive = turtleProperties.penActiveProperty().getValue();
    updateString();
  }

  private void updateString() {
    this.setText(
        myId + ": " + coordinates + "\n   visible - " + visible + "\n   pen active - " + penActive);
  }

}
