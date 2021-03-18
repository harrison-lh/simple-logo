package slogo.view.menubar;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.model.Pen;

/**
 * Selector for color of the canvas background
 *
 * @author David Li
 */
public class PenSelector extends MenuBarSelector<Color> {

  /**
   * Main constructor
   */
  public PenSelector() {
    super("Pen Color", "PenSelector", "PenColorPicker", new ColorPicker((Color) Pen.DEFAULT_COLOR));
  }
}
