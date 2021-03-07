package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {

  private final BackgroundSelector myBackgroundSelector;
  private final GridSelector myGridSelector;
  private final TurtleSelector myTurtleSelector;
  private final PenSelector myPenSelector;

  public MenuBar() {
    this.setId("MenuBar");
    this.getStyleClass().add("box");
    this.setAlignment(Pos.CENTER_LEFT);
    myBackgroundSelector = new BackgroundSelector();
    myGridSelector = new GridSelector();
    myTurtleSelector = new TurtleSelector();
    myPenSelector = new PenSelector();

    this.getChildren()
        .addAll(myBackgroundSelector, myGridSelector, myTurtleSelector, myPenSelector);
  }

  public BackgroundSelector getBackgroundSelector() {
    return myBackgroundSelector;
  }

  public GridSelector getGridSelector() {
    return myGridSelector;
  }

  public TurtleSelector getTurtleSelector() {
    return myTurtleSelector;
  }

  public PenSelector getPenSelector() {
    return myPenSelector;
  }
}
