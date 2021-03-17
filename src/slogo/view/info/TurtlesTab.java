package slogo.view.info;

import javafx.scene.control.Tab;

public class TurtlesTab extends InfoTab {

  private final TurtlesBox myTurtlesBox;

  public TurtlesTab() {
    super("Turtles");
    myTurtlesBox = new TurtlesBox();
    this.setContent(myTurtlesBox);
  }

}
