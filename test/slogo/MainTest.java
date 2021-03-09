package slogo;

import static org.junit.jupiter.api.Assertions.*;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.view.MainView;
import slogo.view.canvas.GridLines;
import slogo.view.menubar.BackgroundSelector;
import util.DukeApplicationTest;

class MainTest extends DukeApplicationTest {

  public static final int WIDTH = 960;
  public static final int HEIGHT = 720;
  public static final int MIN_WIDTH = 960;
  public static final int MIN_HEIGHT = 720;

  @Override
  public void start(Stage stage) {
    MainView mainView = new MainView();
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
  void testElementsGenerated() {
    assertExists("#MenuBar");
    assertExists("#BackgroundSelector");
    assertExists("#GridSelector");
    assertExists("#LanguageSelector");
    assertExists("#PenSelector");
    assertExists("#TurtleSelector");
    assertExists("#InfoButton");
    assertExists("#Grid");
    assertExists("#GridLines");
    assertExists("#TurtleView");
    assertExists("#CommandHistoryBox");
    assertExists("#InputBox");
    assertExists("#UDCommandsBox");
    assertExists("#VariablesBox");
  }

  @Test
  void testBackgroundSelector() {
    ColorPicker backgroundColorPicker = lookup("#BackgroundColorPicker").queryAs(ColorPicker.class);
    Color testColor = Color.RED;
    setValue(backgroundColorPicker, testColor);
    assertEquals(new Background(new BackgroundFill(testColor, CornerRadii.EMPTY, Insets.EMPTY)),
        lookup("#Grid").queryAs(StackPane.class).getBackground());
  }

  @Test
  void testGridSelector() {
    ComboBox<String> gridSelectorComboBox = lookup("#GridSelectorComboBox")
        .queryComboBox();
    String testGridSetting = "Axis";
    select(gridSelectorComboBox, testGridSetting);
    assertTrue(lookup("#GridLines").queryAs(GridLines.class).axisIsVisible());
  }

  private void assertExists(String query) {
    assertNotEquals(lookup(query).query(), null);
  }
}