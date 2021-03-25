package slogo.view.info;

import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * Tab for the info display panel
 *
 * @author David Li
 */
public class InfoTab extends Tab {

  /***
   * Main constructor
   * @param title Title of the tab
   * @param content Content of the tab
   */
  public InfoTab(String title, Node content) {
    this.getStyleClass().add("info-tab");
    this.setText(title);
    this.setClosable(false);
    this.setContent(content);
  }

}
