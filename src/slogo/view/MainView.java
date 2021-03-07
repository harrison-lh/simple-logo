package slogo.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainView extends VBox {

  private MenuBar myMenuBar;
  private Canvas myCanvas;
  private VariablesBox myVariablesBox;
  private UDCommandsBox myUDCommandsBox;
  private InputBox myInputBox;
  private CommandHistoryBox myCommandHistoryBox;

  public MainView() {
    myMenuBar = new MenuBar();
    this.getChildren().add(myMenuBar);

    HBox body = createBody();
    VBox.setVgrow(body, Priority.ALWAYS);
    this.getChildren().add(body);

    HBox bottom = createBottom();
    this.getChildren().add(bottom);
  }

  private HBox createBottom() {
    HBox bottom = new HBox();
    bottom.getStyleClass().add("box");

    myInputBox = new InputBox();
    bottom.getChildren().add(myInputBox);

    myCommandHistoryBox = new CommandHistoryBox();
    bottom.getChildren().add(myCommandHistoryBox);

    return bottom;
  }

  private HBox createBody() {
    HBox body = new HBox();
    body.getStyleClass().add("box");

    myCanvas = new Canvas();
    body.getChildren().add(myCanvas);

    VBox infoBoxes = createInfoBoxes();
    HBox.setHgrow(myCanvas, Priority.ALWAYS);
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
