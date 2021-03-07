package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {

  private final BackgroundSelector myBackgroundSelector;
  private final GridSelector myGridSelector;

  public MenuBar() {
    this.setId("MenuBar");
    this.getStyleClass().add("box");
    this.setAlignment(Pos.CENTER_LEFT);
    myBackgroundSelector = new BackgroundSelector();
    this.getChildren().add(myBackgroundSelector);
    myGridSelector = new GridSelector();
    this.getChildren().add(myGridSelector);
  }

  public BackgroundSelector getBackgroundSelector() {
    return myBackgroundSelector;
  }

  public GridSelector getGridSelector() {
    return myGridSelector;
  }
}
