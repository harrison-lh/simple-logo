package slogo;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.view.MainView;
import util.DukeApplicationTest;

class MainTest extends DukeApplicationTest {

  public static final int WIDTH = 960;
  public static final int HEIGHT = 720;
  public static final int MIN_WIDTH = 960;
  public static final int MIN_HEIGHT = 720;

  @Override
  public void start (Stage stage) {
    MainView mainView = new MainView();
    Scene scene = new Scene(mainView, WIDTH, HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    stage.setScene(scene);
    stage.setMinWidth(MIN_WIDTH);
    stage.setMinHeight(MIN_HEIGHT);
    stage.show();
  }

  @Test
  void testElementsGenerated() {
    assertExists("#MenuBar");
    assertExists("#BackgroundSelector");
    assertExists("#GridSelector");
    assertExists("#LanguageSelector");
    assertExists("#PenSelector");
    assertExists("#TurtleSelector");
    assertExists("#CommandHistoryBox");
    assertExists("#InputBox");
    assertExists("#UDCommandsBox");
    assertExists("#VariablesBox");
  }

  private void assertExists(String query) {
    assertNotEquals(lookup(query).query(), null);
  }
}