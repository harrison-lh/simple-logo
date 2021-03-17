package slogo.view;

import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.view.canvas.CanvasHolder;
import slogo.view.canvas.TurtleCanvas;
import slogo.view.canvas.TurtleView;
import slogo.view.controller.GraphicalController;
import slogo.view.info.CommandsBox;
import slogo.view.info.InfoDisplay;
import slogo.view.info.VariablesBox;
import slogo.view.menubar.MenuBar;

/**
 * MainView class that holds all elements of the GUI
 *
 * @author David Li
 */
public class MainView extends VBox {

  private MenuBar myMenuBar;
  private GraphicalController myGraphicalController;
  private CanvasHolder myCanvasHolder;
  private TurtleCanvas myTurtleCanvas;
  private InfoDisplay myInfoDisplay;
  private VariablesBox myVariablesBox;
  private CommandsBox myCommandsBox;
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
  public PropertyChangeListener getTurtleListener() {
    return myTurtleCanvas;
  }

  /**
   * @return The elements that listens for variable updates in the model
   */
  public PropertyChangeListener getVariablesListener() {
    return myVariablesBox;
  }

  /**
   * @return The elements that listens for user-defined commands updates in the model
   */
  public PropertyChangeListener getCommandsListener() {
    return myCommandsBox;
  }

  /**
   * @return The language selector
   */
  public Selector<String> getLanguageSelector() {
    return myMenuBar.getLanguageSelector();
  }

  /**
   * Sets what happens when the input button is clicked and passes the text from the input box to
   * the response
   *
   * @param response Receiver of user input
   */
  public void setInputAction(Consumer<String> response) {
    myInputBox.setInputAction(e -> {
      String command = myInputBox.getText();
      executeCommand(command, response);
      myInputBox.clear();
    });

    myCommandHistoryBox.setExecuteCommandAction(command -> executeCommand(command, response));
  }

  private void executeCommand(String command, Consumer<String> consumer) {
    try {
      consumer.accept(command);
      myCommandHistoryBox.addCommand(command);
    } catch (IllegalArgumentException | NullPointerException exception) {
      openErrorWindow(exception);
    }
  }

  private void openErrorWindow(Exception exception) {
    Alert alert = new Alert(AlertType.ERROR, exception.getMessage());
    alert.showAndWait();
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

    myGraphicalController = new GraphicalController();

    myCanvasHolder = new CanvasHolder();
    myTurtleCanvas = myCanvasHolder.getTurtleCanvas();
    myTurtleView = myCanvasHolder.getTurtleView();

    myInfoDisplay = new InfoDisplay();
    myVariablesBox = myInfoDisplay.getVariablesBox();
    myCommandsBox = myInfoDisplay.getCommandsBox();

    HBox.setHgrow(myCanvasHolder, Priority.ALWAYS);

    body.getChildren().addAll(myGraphicalController, myCanvasHolder, myInfoDisplay);

    return body;
  }
}
