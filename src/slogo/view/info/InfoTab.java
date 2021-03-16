package slogo.view.info;

import javafx.scene.control.Tab;

public abstract class InfoTab extends Tab {

  public InfoTab(String title) {
    this.getStyleClass().add("info-tab");
    this.setText(title);
    this.setClosable(false);
  }

}
