package slogo.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * Palette implements a color Palette for SLogo objects to pull from.
 *
 * @author Marc Chmielewski
 */
public class Palette {

  List<Color> colors;

  /**
   * Default Palette constructor.
   *
   * Creates a Palette with Black and the ROYGBIV colors.
   */
  public Palette() {
    colors = new ArrayList<>(List.of(Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW,
        Color.GREEN, Color.BLUE, Color.INDIGO, Color.VIOLET));
  }

  /**
   * Set the Color at the provided index of this Palette, with the RGB value provided.
   * This function contains logic to auto-range out of range RGB values to avoid throwing errors.
   *
   * @param index The index of the Palette to modify.
   * @param red The R value for the new Color.
   * @param green The G value for the new Color.
   * @param blue The B value for the new Color.
   */
  public void setColorAtIndex(int index, int red, int green, int blue) {
    Color newColor = Color.rgb(Math.max(0, Math.min(red, 255)),
        Math.max(0, Math.min(green, 255)), Math.max(0, Math.min(blue, 255)));
    colors.set(index, newColor);
  }

  /**
   * Get the Color from the provided index of this Palette.
   *
   * @param index The index of the Palette to retrieve the Color from.
   * @return The Color at the index of this Palette.
   */
  public Color getColorAtIndex(int index) {
    return colors.get(index);
  }
}
