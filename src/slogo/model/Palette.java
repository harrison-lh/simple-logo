package slogo.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

/**
 * Palette implements a color Palette for SLogo objects to pull from.
 *
 * @author Marc Chmielewski
 */
public class Palette {

  private final ListProperty<Color> colorsProperty;
  List<Color> colors;

  /**
   * Default Palette constructor.
   * <p>
   * Creates a Palette with Black and the ROYGBIV colors.
   */
  public Palette() {
    colors = new ArrayList<>(List.of(Color.WHITE, Color.BLACK, Color.GRAY, Color.RED, Color.ORANGE,
        Color.YELLOW, Color.GREEN, Color.BLUE, Color.INDIGO, Color.VIOLET));
    colorsProperty = new SimpleListProperty<>(FXCollections.observableArrayList(colors));
  }

  /**
   * Set the Color at the provided index of this Palette, with the RGB value provided. This function
   * contains logic to auto-range out of range RGB values to avoid throwing errors.
   *
   * @param index The index of the Palette to modify.
   * @param red   The R value for the new Color.
   * @param green The G value for the new Color.
   * @param blue  The B value for the new Color.
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

  /**
   * Gets a ListProperty representation of the colors in this Palette.
   *
   * @return A ListProperty of Colors containing the Colors in this Palette.
   */
  public ListProperty<Color> getColorsProperty() {
    return colorsProperty;
  }
}
