package slogo.view.menubar;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.controller.GlobalProperties;

/**
 * Selector for pen color
 *
 * @author David Li
 */
public class PenSelector extends MenuBarSelector<Color> {

  /**
   * Main constructor
   */
  public PenSelector() {
    super("Pen Color", "PenSelector", "PenColorPicker",
        new ColorPicker(GlobalProperties.DEFAULT_PEN_COLOR));
  }
}
