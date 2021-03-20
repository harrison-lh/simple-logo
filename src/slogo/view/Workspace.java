package slogo.view;

import javafx.scene.control.Tab;
import slogo.controller.Controller;

/**
 * A single workspace as a tab, creates new controller and view.
 *
 * @author David Li
 */
public class Workspace extends Tab {

  /**
   * Main constructor
   */
  public Workspace() {
    this.setText("workspace");
    Controller controller = new Controller();
    MainView mainView = controller.getMainView();
    this.setContent(mainView);
  }
}
