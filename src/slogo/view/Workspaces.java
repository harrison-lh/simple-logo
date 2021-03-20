package slogo.view;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * Holds all workspaces in multiple tabs and has create new tab button.
 * Has a maximum number of workspaces.
 *
 * @author David Li
 */
public class Workspaces extends AnchorPane {

  private static final int MAX_WORKSPACES = 10;

  private final TabPane myTabs;
  private final Button myButton;

  /**
   * Main constructor
   */
  public Workspaces() {
    myTabs = new TabPane();
    myTabs.setId("WorkspaceTabPane");
    myButton = new Button("+");
    myButton.setId("NewTab");
    myButton.setOnAction(e -> createNewTab());

    AnchorPane.setTopAnchor(myTabs, 0.0);
    AnchorPane.setLeftAnchor(myTabs, 0.0);
    AnchorPane.setRightAnchor(myTabs, 0.0);
    AnchorPane.setBottomAnchor(myTabs, 0.0);
    AnchorPane.setTopAnchor(myButton, 4.0);
    AnchorPane.setRightAnchor(myButton, 10.0);
    createNewTab();

    this.getChildren().addAll(myTabs, myButton);
  }

  private void createNewTab() {
    Workspace workspace = new Workspace();
    workspace.setOnClosed(e -> {
      if (myTabs.getTabs().size() < MAX_WORKSPACES) {
        myButton.setDisable(false);
      }
    });
    myTabs.getTabs().add(workspace);
    if (myTabs.getTabs().size() >= MAX_WORKSPACES) {
      myButton.setDisable(true);
    }
    myTabs.getSelectionModel().selectLast();
  }

}
