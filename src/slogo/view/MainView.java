package slogo.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.view.canvas.SLogoCanvas;
import slogo.view.menubar.MenuBar;

public class MainView extends VBox {

  private MenuBar myMenuBar;
  private SLogoCanvas mySLogoCanvas;
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

    connectColorSelector(mySLogoCanvas, myMenuBar.getBackgroundSelector());
    connectStringSelector(mySLogoCanvas.getGrid(), myMenuBar.getGridSelector());
    connectStringSelector(mySLogoCanvas.getTurtleView(), myMenuBar.getTurtleSelector());
  }

  public void resizeElements() {
    mySLogoCanvas.resizeElements();
  }

  private void connectColorSelector(SelectorTarget<Color> target, Selector<Color> selector) {
    selector.setUpdateAction(target.updateAction());
  }

  private void connectStringSelector(SelectorTarget<String> target, Selector<String> selector) {
    selector.setUpdateAction(target.updateAction());
  }

  private HBox createBottom() {
    HBox bottom = new HBox();

    myInputBox = new InputBox();
    bottom.getChildren().add(myInputBox);

    myCommandHistoryBox = new CommandHistoryBox();
    bottom.getChildren().add(myCommandHistoryBox);
    HBox.setHgrow(myCommandHistoryBox, Priority.ALWAYS);

    return bottom;
  }

  private HBox createBody() {
    HBox body = new HBox();

    mySLogoCanvas = new SLogoCanvas();
    body.getChildren().add(mySLogoCanvas);

    VBox infoBoxes = createInfoBoxes();
    body.getChildren().add(infoBoxes);

    HBox.setHgrow(mySLogoCanvas, Priority.ALWAYS);

    return body;
  }

  private VBox createInfoBoxes() {
    VBox infoBoxes = new VBox();
    infoBoxes.setId("InfoBoxes");

    myVariablesBox = new VariablesBox();
    infoBoxes.getChildren().add(myVariablesBox);

    myUDCommandsBox = new UDCommandsBox();
    infoBoxes.getChildren().add(myUDCommandsBox);

    VBox.setVgrow(myVariablesBox, Priority.SOMETIMES);
    VBox.setVgrow(myUDCommandsBox, Priority.SOMETIMES);

    return infoBoxes;
  }
}
