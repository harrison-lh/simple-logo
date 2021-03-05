package slogo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MenuBar extends HBox {

  public MenuBar() {
    this.setId("MenuBar");
    this.getChildren().add(new Label("menu bar"));
  }
}
