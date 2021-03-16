package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.controller.Parser;
import slogo.model.Turtle;
import slogo.view.MainView;
import util.DukeApplicationTest;

class ListenersTests extends DukeApplicationTest {

  private Turtle myTurtle;
  private Parser myParser;

  @Override
  public void start(Stage stage) {
    Controller controller = new Controller();
    MainView mainView = controller.getMainView();
    myTurtle = controller.getTurtle();
    Scene scene = new Scene(mainView, Main.MIN_WIDTH, Main.MIN_HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    stage.setScene(scene);
    stage.setMinWidth(Main.MIN_WIDTH);
    stage.setMinHeight(Main.MIN_HEIGHT);
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

//  @Test
//  void testModelToView() {
//    double initHeading = myTurtle.getHeading();
//    myTurtle.right(50);
//    assertEquals(initHeading - 50, lookup("#TurtleView").queryAs(TurtleView.class).getHeading());
//  }
}