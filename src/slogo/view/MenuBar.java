package slogo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {

  private BackgroundSelector myBackgroundSelector;

  public MenuBar() {
    this.setId("MenuBar");
    this.getStyleClass().add("box");
    this.getChildren().add(new Label("menu bar"));
    myBackgroundSelector = new BackgroundSelector();
    this.getChildren().add(myBackgroundSelector);
  }

  public BackgroundSelector getBackgroundSelector() {
    return myBackgroundSelector;
  }
}
