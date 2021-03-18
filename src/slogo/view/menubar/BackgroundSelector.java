package slogo.view.menubar;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/**
 * Selector for color of the canvas background
 *
 * @author David Li
 */
public class BackgroundSelector extends MenuBarSelector<Color> {

  private static final Color DEFAULT_CANVAS_COLOR = Color.WHITE;

  /**
   * Main constructor
   */
  public BackgroundSelector() {
    super("Canvas Background", "BackgroundSelector", "BackgroundColorPicker",
        new ColorPicker(DEFAULT_CANVAS_COLOR));
  }
}
