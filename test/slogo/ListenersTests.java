package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.controller.Parser;
import slogo.model.Turtle;
import slogo.view.CommandHistoryBox;
import slogo.view.MainView;
import slogo.view.canvas.TurtleView;
import util.DukeApplicationTest;

class ListenersTests extends DukeApplicationTest {

  public static final int WIDTH = 960;
  public static final int HEIGHT = 720;
  public static final int MIN_WIDTH = 960;
  public static final int MIN_HEIGHT = 720;

  private Turtle myTurtle;
  private Parser myParser;

  @Override
  public void start(Stage stage) {
    Controller controller = new Controller();
    myParser = controller.getParser();
    myTurtle = controller.getTurtle();
    MainView mainView = controller.getMainView();
    Scene scene = new Scene(mainView, WIDTH, HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    stage.setScene(scene);
    stage.setMinWidth(MIN_WIDTH);
    stage.setMinHeight(MIN_HEIGHT);
    stage.widthProperty().addListener((obs, oldVal, newVal) -> mainView.resizeElements());
    stage.heightProperty().addListener((obs, oldVal, newVal) -> mainView.resizeElements());
    stage.show();
  }

  @Test
  void testInputToParser() {
    String command = "fd 50";
    double initY = myTurtle.getY();
    TextArea inputBoxArea = lookup("#InputBoxArea").queryAs(TextArea.class);
    inputBoxArea.setText(command);
    clickOn(lookup("#InputButton").queryButton());
    assertEquals(myTurtle.getY(), initY + 50);
  }

  @Test
  void testModelToView() {
    double initHeading = myTurtle.getHeading();
    myTurtle.right(50);
    assertEquals(initHeading - 50, lookup("#TurtleView").queryAs(TurtleView.class).getHeading());
  }
}