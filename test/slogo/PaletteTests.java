package slogo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.Palette;

/**
 * A suite of tests to put the Palette through its paces.
 *
 * @author Marc Chmielewski
 */
public class PaletteTests {

  private Palette palette;
  private int red;
  private int green;
  private int blue;

  @BeforeEach
  public void setup() {
    Random rng = new Random();
    palette = new Palette();
    red = rng.nextInt(255);
    green = rng.nextInt(255);
    blue = rng.nextInt(255);
  }

  @Test
  public void testDefaultPaletteConstructor() {
    assertEquals(palette.getColorAtIndex(0), Color.WHITE);
    assertEquals(palette.getColorAtIndex(7), Color.BLUE);
  }

  @Test
  public void testSetPaletteColorAtIndex() {
    palette.setColorAtIndex(0, red, green, blue);
    assertEquals(palette.getColorAtIndex(0).getRed(), red/255., 0.01);
    assertEquals(palette.getColorAtIndex(0).getGreen(), green/255., 0.01);
    assertEquals(palette.getColorAtIndex(0).getBlue(), blue/255., 0.01);
  }

  @Test
  public void testExtremeColorValues() {
    palette.setColorAtIndex(0, -31415, 69, 420);
    assertEquals(palette.getColorAtIndex(0).getRed(), 0/255., 0.01);
    assertEquals(palette.getColorAtIndex(0).getGreen(), 69/255., 0.01);
    assertEquals(palette.getColorAtIndex(0).getBlue(), 255/255., 0.01);
  }
}
