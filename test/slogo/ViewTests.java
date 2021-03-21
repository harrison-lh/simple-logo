package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Deque;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.view.CommandHistoryBox;
import slogo.view.Workspaces;
import slogo.view.canvas.GridLines;
import slogo.view.canvas.TurtleCanvas;
import slogo.view.canvas.TurtleView;
import slogo.view.info.VariablesBox;
import slogo.view.menubar.MenuBar;
import util.DukeApplicationTest;

class ViewTests extends DukeApplicationTest {

  @Override
  public void start(Stage stage) {
    Scene scene = new Scene(new Workspaces(), Main.MIN_WIDTH, Main.MIN_HEIGHT);
    scene.getStylesheets().add("slogo/view/stylesheet.css");
    stage.setScene(scene);
    stage.setMinWidth(Main.MIN_WIDTH);
    stage.setMinHeight(Main.MIN_HEIGHT);
    stage.setTitle("SLogo");
    stage.show();
  }

  @Test
  void testElementsGenerated() {
    assertExists("#NewTab");
    assertExists("#MenuBar");
    assertExists("#BackgroundSelector");
    assertExists("#GridSelector");
    assertExists("#LanguageSelector");
    assertExists("#PenSelector");
    assertExists("#TurtleSelector");
    assertExists("#InfoButton");
    assertExists("#GraphicalController");
    assertExists("#TurtleCanvas");
    assertExists("#GridLines");
    assertExists("#TurtleView");
    assertExists("#CommandHistoryBox");
    assertExists("#InputBox");
    assertExists("#TurtlesBox");
    assertExists("#CommandsBox");
    assertExists("#VariablesBox");
    assertExists("#PalettesBox");
  }

  @Test
  void testBackgroundSelector() {
    ColorPicker backgroundColorPicker = lookup("#BackgroundColorPicker").queryAs(ColorPicker.class);
    Color testColor = Color.RED;
    // Select background color
    setValue(backgroundColorPicker, testColor);
    // Check if grid background color updates
    assertEquals(new Background(new BackgroundFill(testColor, CornerRadii.EMPTY, Insets.EMPTY)),
        lookup("#TurtleCanvas").queryAs(StackPane.class).getBackground());
  }

  @Test
  void testGridSelector() {
    ComboBox<String> gridSelectorComboBox = lookup("#GridSelectorComboBox")
        .queryComboBox();
    String testGridSetting = "Axis";
    // Select axis grid type
    select(gridSelectorComboBox, testGridSetting);
    // Check if axes are visible
    Boolean axesAreVisible = (Boolean) getPrivateField(
        lookup("#GridLines").queryAs(GridLines.class), "axesAreVisible");
    assertTrue(axesAreVisible);
  }

  @Test
  void testTurtleSelector() {
    ComboBox<String> turtleSelectorComboBox = lookup("#TurtleSelectorComboBox")
        .queryComboBox();
    String testTurtle = "Realistic";
    // Select realistic turtle
    select(turtleSelectorComboBox, testTurtle);
    // Check if turtle image filename is correct
    assertEquals("turtle-realistic.png",
        invokePrivateMethod(lookup("#TurtleView").queryAs(TurtleView.class),
            "getTurtleImageFilename"));
  }

  @Test
  void testPenSelector() {
    ColorPicker penColorPicker = lookup("#PenColorPicker").queryAs(ColorPicker.class);
    Color testColor = Color.RED;
    // Select pen color
    setValue(penColorPicker, testColor);
    // Check if pen color updates
    assertEquals(testColor,
        lookup("#TurtleCanvas").queryAs(TurtleCanvas.class).getPen().getColor());
  }

  @Test
  void testLanguageSelector() {
    ComboBox<String> languageSelectorComboBox = lookup("#LanguageComboBox")
        .queryComboBox();
    String testLanguage = "French";
    // Select French language
    select(languageSelectorComboBox, testLanguage);
    // Check if language consumers get updated
    assertEquals(testLanguage,
        getPrivateField(lookup("#VariablesBox").queryAs(VariablesBox.class),
            "language"));
  }

  @Test
  void testNewTab() {
    clickOn(lookup("#NewTab").queryButton());
    assertEquals(2, lookup("#WorkspaceTabPane").queryAs(TabPane.class).getTabs().size());
  }

  @Test
  void testMovementController() {
    double movementDistance = 50;
    lookup("#MovementInput").queryAs(TextField.class).setText(String.valueOf(movementDistance));
    clickOn(lookup("#ControllerForwardButton").queryButton());
    assertEquals(movementDistance,
        lookup("#TurtleView").queryAs(TurtleView.class).getYCoordinate());
    clickOn(lookup("#ControllerBackwardButton").queryButton());
    assertEquals(0, lookup("#TurtleView").queryAs(TurtleView.class).getYCoordinate());
  }

  @Test
  void testRotationController() {
    double rotationAngle = 90;
    double initialHeading = lookup("#TurtleView").queryAs(TurtleView.class).getHeading();
    lookup("#RotationInput").queryAs(TextField.class).setText(String.valueOf(rotationAngle));
    clickOn(lookup("#ControllerLeftButton").queryButton());
    assertEquals(initialHeading + rotationAngle,
        lookup("#TurtleView").queryAs(TurtleView.class).getHeading());
    clickOn(lookup("#ControllerRightButton").queryButton());
    assertEquals(initialHeading, lookup("#TurtleView").queryAs(TurtleView.class).getHeading());
  }

//  @Test
//  void testPenController() {
//    clickOn(lookup("#ControllerPenUpButton").queryButton());
//    assertFalse(lookup("#TurtleCanvas").queryAs(TurtleCanvas.class).getPen().isPenActive());
//    clickOn(lookup("#ControllerPenDownButton").queryButton());
//    assertTrue(lookup("#TurtleCanvas").queryAs(TurtleCanvas.class).getPen().isPenActive());
//  }

  @Test
  void testClearController() {
    lookup("#MovementInput").queryAs(TextField.class).setText("50");
    clickOn(lookup("#ControllerForwardButton").queryButton());

    clickOn(lookup("#ControllerClearButton").queryButton());
    assertEquals(0, lookup("#TurtleView").queryAs(TurtleView.class).getYCoordinate());
  }

  @Test
  void testHelpWindow() {
    clickOn(lookup("#InfoButton").queryButton());
    assertTrue(lookup("#MenuBar").queryAs(MenuBar.class).helpWindowIsOpen());
  }

  @Test
  void testInputCommand() {
    String command = "fd 50";
    TextArea inputBoxArea = lookup("#InputBoxArea").queryAs(TextArea.class);
    inputBoxArea.setText(command);
    clickOn(lookup("#InputButton").queryButton());
    assertEquals(command,
        ((Deque<String>) getPrivateField(
            lookup("#CommandHistoryBox").queryAs(CommandHistoryBox.class),
            "pastCommands")).poll());
    assertEquals("", inputBoxArea.getText());
  }

  private void assertExists(String query) {
    assertNotEquals(lookup(query).query(), null);
  }

  private Object getPrivateField(Object object, String field) {
    try {
      Field f = object.getClass().getDeclaredField(field);
      f.setAccessible(true);
      return f.get(object);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      fail("Can't access field");
      return null;
    }
  }

  private Object invokePrivateMethod(Object object, String methodName) {
    try {
      Method method = object.getClass().getDeclaredMethod(methodName);
      method.setAccessible(true);
      return method.invoke(object);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      fail("Can't access method");
      return null;
    }
  }
}