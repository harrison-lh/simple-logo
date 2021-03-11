package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.view.Selector;

/**
 * Selector for color of the canvas background
 *
 * @author David Li
 */
public class PenSelector extends VBox implements Selector<Color> {

  private final ColorPicker myColorPicker;
  public static final Color DEFAULT_PEN_COLOR = Color.BLACK;

  /**
   * Main constructor
   */
  public PenSelector() {
    this.setId("PenSelector");
    this.getStyleClass().add("selector");
    Label myLabel = new Label("Pen Color");
    myColorPicker = new ColorPicker(DEFAULT_PEN_COLOR);

    this.getChildren().addAll(myLabel, myColorPicker);
  }

  /**
   * Passes the selected color to the consumer
   */
  @Override
  public void setUpdateAction(Consumer<Color> response) {
    myColorPicker.setOnAction(e -> response.accept(myColorPicker.getValue()));
  }
}
