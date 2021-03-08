package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.view.Selector;

public class BackgroundSelector extends VBox implements Selector<Color> {

  private final ColorPicker myColorPicker;
  public static final Color DEFAULT_CANVAS_COLOR = Color.WHITE;

  public BackgroundSelector() {
    this.setId("BackgroundSelector");
    this.getStyleClass().add("selector");
    Label myLabel = new Label("Canvas Background");
    myColorPicker = new ColorPicker(DEFAULT_CANVAS_COLOR);

    this.getChildren().addAll(myLabel, myColorPicker);
  }

  @Override
  public void setUpdateAction(Consumer<Color> response) {
    myColorPicker.setOnAction(e -> response.accept(myColorPicker.getValue()));
  }
}
