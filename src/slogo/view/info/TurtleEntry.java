package slogo.view.info;

import javafx.scene.control.Label;
import slogo.controller.TurtleProperties;

public class TurtleEntry extends Label {

  private final int myId;
  private String coordinates;
  private boolean visible;
  private boolean penActive;

  public TurtleEntry(int id, TurtleProperties turtleProperties) {
    myId = id;
    coordinates = turtleProperties.getCoordinates().toString();
    visible = turtleProperties.visibleProperty().getValue();
    turtleProperties.getCoordinates().stringProperty().addListener(((observable, oldValue, newValue) -> {
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
    updateString();
  }

  private void updateString() {
    this.setText(myId + ": " + coordinates + "\n   visible - " + visible + "\n   pen active - " + penActive);
  }

}
