package slogo.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

  private MenuBar myMenuBar;
  private Canvas myCanvas;
  private VariablesBox myVariablesBox;
  private UDCommandsBox myUDCommandsBox;

  public MainView() {
    myMenuBar = new MenuBar();
    this.getChildren().add(myMenuBar);

    HBox body = createBody();
    this.getChildren().add(body);
  }

  private HBox createBody() {
    HBox body = new HBox();
    body.getStyleClass().add("box");

    myCanvas = new Canvas();
    body.getChildren().add(myCanvas);

    VBox infoBoxes = createInfoBoxes();
    body.getChildren().add(infoBoxes);

    return body;
  }

  private VBox createInfoBoxes() {
    VBox infoBoxes = new VBox();

    myVariablesBox = new VariablesBox();
    infoBoxes.getChildren().add(myVariablesBox);

    myUDCommandsBox = new UDCommandsBox();
    infoBoxes.getChildren().add(myUDCommandsBox);

    return infoBoxes;
  }
}
