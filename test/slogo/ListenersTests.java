package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.controller.Controller;
import slogo.view.CommandHistoryBox;
import slogo.view.MainView;
import slogo.view.canvas.GridLines;
import slogo.view.canvas.TurtleView;
import util.DukeApplicationTest;

class ListenersTests extends DukeApplicationTest {

  public static final int WIDTH = 960;
  public static final int HEIGHT = 720;
  public static final int MIN_WIDTH = 960;
  public static final int MIN_HEIGHT = 720;

  @Override
  public void start(Stage stage) {
    Controller controller = new Controller();
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

}