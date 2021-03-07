package slogo.view;

import java.util.function.Consumer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PenSelector extends VBox implements Selector<Color> {

  private final ColorPicker myColorPicker;
  public static final Color DEFAULT_PEN_COLOR = Color.BLACK;

  public PenSelector() {
    Label myLabel = new Label("Pen Color");
    myColorPicker = new ColorPicker(DEFAULT_PEN_COLOR);

    this.getChildren().addAll(myLabel, myColorPicker);
  }

  @Override
  public void setUpdateAction(Consumer<Color> response) {
    myColorPicker.setOnAction(e -> response.accept(myColorPicker.getValue()));
  }
}
