package slogo.view;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import slogo.controller.GlobalProperties;
import slogo.controller.TurtleProperties;
import slogo.view.canvas.CanvasHolder;
import slogo.view.canvas.TurtleCanvas;
import slogo.view.controller.GraphicalController;
import slogo.view.info.CommandsBox;
import slogo.view.info.InfoDisplay;
import slogo.view.info.PalettesBox;
import slogo.view.info.TurtlesBox;
import slogo.view.info.VariablesBox;
import slogo.view.menubar.MenuBar;

/**
 * MainView class that holds all elements of the GUI
 *
 * @author David Li
 */
public class MainView extends BorderPane {

  private final MenuBar myMenuBar;
  private GraphicalController myGraphicalController;
  private CanvasHolder myCanvasHolder;
  private TurtleCanvas myTurtleCanvas;
  private InfoDisplay myInfoDisplay;
  private TurtlesBox myTurtlesBox;
  private VariablesBox myVariablesBox;
  private CommandsBox myCommandsBox;
  private PalettesBox myPalettesBox;
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

    myMenuBar.getLanguageSelector()
        .addLanguageConsumers(myGraphicalController.getLanguageConsumers());
    myMenuBar.getLanguageSelector().addLanguageConsumers(myVariablesBox);
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
    myCommandsBox.setExecuteCommandAction(command -> executeCommand(command, response));
    myVariablesBox.setExecuteCommandAction(command -> executeCommand(command, response));
    myGraphicalController.setExecuteCommandAction(command -> executeCommand(command, response));
    myMenuBar.getFileButtons().setCommandConsumer(command -> executeCommand(command, response));
  }

  /**
   * @return consumer that accepts turtle properties of a new turtle to create a new turtle
   * in the view
   */
  public Consumer<TurtleProperties> newTurtleConsumer() {
    return turtleProperties -> {
      myTurtleCanvas.newTurtleConsumer().accept(turtleProperties);
      myTurtlesBox.addTurtle(turtleProperties);
    };
  }

  /**
   * Accepts global properties object and binds respective view elements to the properties
   * @param globalProperties global properties object in the back-end
   */
  public void setGlobalProperties(GlobalProperties globalProperties) {
    myGlobalProperties = globalProperties;
    bindGlobalProperties();
    bindSelectorsToGlobalProperties();
  }

  private void bindSelectorsToGlobalProperties() {
    myMenuBar.getPenSelector().setGlobalProperty(myGlobalProperties.penColorPropertyProperty());
    myMenuBar.getTurtleSelector()
        .setGlobalProperty(myGlobalProperties.turtleShapePropertyProperty());
    myMenuBar.getBackgroundSelector()
        .setGlobalProperty(myGlobalProperties.backgroundColorPropertyProperty());
  }

  private void bindGlobalProperties() {
    myGlobalProperties.backgroundColorPropertyProperty()
        .addListener(((observable, oldValue, newValue) -> {
          myTurtleCanvas.setBackground(new Background(
              new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
        }));
    myGlobalProperties.penColorPropertyProperty().addListener(((observable, oldValue, newValue) -> {
      myTurtleCanvas.getPen().setColor(newValue);
    }));
    myGlobalProperties.penSizePropertyProperty().addListener(((observable, oldValue, newValue) -> {
      myTurtleCanvas.getPen().setSize(newValue.doubleValue());
    }));
    myGlobalProperties.turtleShapePropertyProperty()
        .addListener(((observable, oldValue, newValue) -> {
          myTurtleCanvas.setTurtleShape(newValue);
        }));
    myGlobalProperties.paletteProperty().addListener(((observable, oldValue, newValue) -> {
      myPalettesBox.setColors(newValue);
    }));
    myGlobalProperties.variableMapPropertyProperty()
        .addListener(((observable, oldValue, newValue) -> {
          myVariablesBox.setVariables(newValue);
        }));
    myGlobalProperties.userCommandsProperty().addListener(((observable, oldValue, newValue) -> {
//      System.out.println("new command");
      myCommandsBox.setCommands(newValue);
    }));
    myPalettesBox.setColors(myGlobalProperties.paletteProperty());
    myPalettesBox.setShapes(myGlobalProperties.getShapeMap());
    myMenuBar.getFileButtons().setVariablesMap(myGlobalProperties.getVariablesMap());
    myMenuBar.getFileButtons().setCommands(myGlobalProperties.getCommandsMap());
    myGlobalProperties.addClearScreenListener(e -> clearScreen());
  }

  private void clearScreen() {
    myTurtleCanvas.clearScreen();
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
    myTurtlesBox = myInfoDisplay.getTurtlesBox();
    myVariablesBox = myInfoDisplay.getVariablesBox();
    myCommandsBox = myInfoDisplay.getCommandsBox();
    myPalettesBox = myInfoDisplay.getPalettesBox();

    this.setCenter(myCanvasHolder);
    this.setLeft(myGraphicalController);
    this.setRight(myInfoDisplay);
  }
}
