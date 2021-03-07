package slogo.view;

import java.util.function.Consumer;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class BackgroundSelector extends ColorPicker implements Selector<Color> {

  @Override
  public void setUpdateAction(Consumer<Color> response) {
    this.setOnAction(e -> response.accept(this.getValue()));
  }
}
