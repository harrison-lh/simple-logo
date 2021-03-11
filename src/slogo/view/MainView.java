package slogo.view;

import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.controller.Command;
import slogo.model.Variables;
import slogo.view.canvas.CanvasHolder;
import slogo.view.canvas.TurtleCanvas;
import slogo.view.canvas.TurtleView;
import slogo.view.menubar.MenuBar;

/**
 * MainView class that holds all elements of the GUI
 *
 * @author David Li
 */
public class MainView extends VBox implements View {

  private MenuBar myMenuBar;
  private CanvasHolder myCanvasHolder;
  private TurtleCanvas myTurtleCanvas;
  private VariablesBox myVariablesBox;
  private UDCommandsBox myUDCommandsBox;
  private InputBox myInputBox;
  private CommandHistoryBox myCommandHistoryBox;
  private TurtleView myTurtleView;

  /**
   * Main constructor
   */
  public MainView() {
    myMenuBar = new MenuBar();
    this.getChildren().add(myMenuBar);

    HBox body = createBody();
    VBox.setVgrow(body, Priority.ALWAYS);
    this.getChildren().add(body);

    HBox bottom = createBottom();
    this.getChildren().add(bottom);

    connectColorSelector(myCanvasHolder, myMenuBar.getBackgroundSelector());
    connectStringSelector(myTurtleCanvas, myMenuBar.getGridSelector());
    connectStringSelector(myTurtleView, myMenuBar.getTurtleSelector());
    connectColorSelector(myTurtleCanvas.getPen(), myMenuBar.getPenSelector());
  }

  /**
   * @return The elements that listens for turtle updates in the model
   */
  public PropertyChangeListener getListener() {
    return myTurtleCanvas;
  }

  /**
   * Adjust the size of elements when the window changes size
   */
  public void resizeElements() {
    myCanvasHolder.resizeElements();
  }

  @Override
  public void addVariable(Variables variables) {

  }

  @Override
  public void updateVariable(Variables variables) {

  }

  @Override
  public void removeVariable(Variables variables) {

  }

  @Override
  public void addUDCommand(Command command) {

  }

  @Override
  public void updateUDCommand(Command command) {

  }

  @Override
  public void removeUDCommand(Command command) {

  }

  @Override
  public void throwError(Error error) {

  }

  /**
   * Sets what happens when the input button is clicked and passes
   * the text from the input box to the response
   * @param response Receiver of user input
   */
  public void setInputAction(Consumer<String> response) {
    myInputBox.setInputAction(e -> {
      String command = myInputBox.getText();
      response.accept(command);
      myInputBox.clear();
      myCommandHistoryBox.addCommand(command);
    });
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

    myCanvasHolder = new CanvasHolder();
    myTurtleCanvas = myCanvasHolder.getTurtleCanvas();
    myTurtleView = myCanvasHolder.getTurtleView();
    body.getChildren().add(myCanvasHolder);

    VBox infoBoxes = createInfoBoxes();
    body.getChildren().add(infoBoxes);

    HBox.setHgrow(myCanvasHolder, Priority.ALWAYS);

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
