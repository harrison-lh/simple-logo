package slogo.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

  private MenuBar myMenuBar;
  private Canvas myCanvas;

  public MainView() {
    myMenuBar = new MenuBar();
    this.getChildren().add(myMenuBar);

    HBox body = createBody();
    this.getChildren().add(body);
  }

  private HBox createBody() {
    HBox body = new HBox();
    myCanvas = new Canvas();
    body.getChildren().add(myCanvas);
    body.getStyleClass().add("box");

    return body;
  }
}
