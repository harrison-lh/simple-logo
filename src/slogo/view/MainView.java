package slogo.view;

import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.controller.GlobalProperties;
import slogo.controller.TurtleProperties;
import slogo.model.Coordinates;
import slogo.model.Turtle;
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
public class MainView extends BorderPane {

  private MenuBar myMenuBar;
  private GraphicalController myGraphicalController;
  private CanvasHolder myCanvasHolder;
  private static TurtleCanvas myTurtleCanvas;
  private InfoDisplay myInfoDisplay;
  private VariablesBox myVariablesBox;
  private CommandsBox myCommandsBox;
  private InputBox myInputBox;
  private CommandHistoryBox myCommandHistoryBox;
  private GlobalProperties myGlobalProperties;

  /**
   * Main constructor
   */
  public MainView() {
    createBody();
    myMenuBar = new MenuBar();
    this.setTop(myMenuBar);

    HBox bottom = createBottom();
    this.setBottom(bottom);

    connectColorSelector(myCanvasHolder, myMenuBar.getBackgroundSelector());
    connectStringSelector(myTurtleCanvas, myMenuBar.getGridSelector());
    connectColorSelector(myTurtleCanvas.getPen(), myMenuBar.getPenSelector());

    myMenuBar.getLanguageSelector().addLanguageConsumers(myGraphicalController.getLanguageConsumers());
    myMenuBar.getLanguageSelector().addLanguageConsumers(myVariablesBox);
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

  public Consumer<String> turtleShapeConsumer() {
    return shape -> {
      // change turtle shapes
      // select turtle image selector
    };
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
    myCommandsBox.setExecuteCommandAction(command -> executeCommand(command, response));
    myVariablesBox.setExecuteCommandAction(command -> executeCommand(command, response));
    myGraphicalController.setExecuteCommandAction(command -> executeCommand(command, response));
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

  private void createBody() {
    myGraphicalController = new GraphicalController();

    myCanvasHolder = new CanvasHolder();
    myTurtleCanvas = myCanvasHolder.getTurtleCanvas();

    myInfoDisplay = new InfoDisplay();
    myVariablesBox = myInfoDisplay.getVariablesBox();
    myCommandsBox = myInfoDisplay.getCommandsBox();

    this.setCenter(myCanvasHolder);
    this.setLeft(myGraphicalController);
    this.setRight(myInfoDisplay);
  }

  /**
   * Creates a new turtle in the canvas according to the parameters passed by the Controller.
   *
   * @param turtleProperties The properties of the turtle
   */
  public void createTurtle(TurtleProperties turtleProperties) {
    myTurtleCanvas.createTurtle(turtleProperties);
  }

  public static void setBackgroundColor(Color newColor) {
    myTurtleCanvas.setBackground(new Background(
        new BackgroundFill(newColor, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public Consumer<TurtleProperties> newTurtleConsumer() {
    return myTurtleCanvas.newTurtleConsumer();
  }

  public void setGlobalProperties(GlobalProperties globalProperties) {
    myGlobalProperties = globalProperties;
    myGlobalProperties.backgroundColorPropertyProperty().addListener(((observable, oldValue, newValue) -> {
      myTurtleCanvas.setBackground(new Background(
          new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
    }));
    myGlobalProperties.penColorPropertyProperty().addListener(((observable, oldValue, newValue) -> {
      myTurtleCanvas.getPen().setColor(newValue);
    }));
    myGlobalProperties.penSizePropertyProperty().addListener(((observable, oldValue, newValue) -> {
      myTurtleCanvas.getPen().setSize(newValue.doubleValue());
    }));
  }
}
