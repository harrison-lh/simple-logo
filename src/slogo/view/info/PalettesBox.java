package slogo.view.info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Display box for palettes
 *
 * @author David Li
 */
public class PalettesBox extends ScrollPane {

  private final VBox myContents;
  private final List<ColorPalatteEntry> myColorPaletteEntries;
  private final VBox myColorPalette;
  private final VBox myShapePalette;

  /**
   * Main constructor
   */
  public PalettesBox() {
    this.setId("PalettesBox");
    this.getStyleClass().add("info-box");
    myContents = new VBox();
    myColorPalette = new VBox();
    myColorPalette.setSpacing(5);
    myColorPalette.getChildren().add(new Label("Color Palette"));
    myShapePalette = new VBox();
    myShapePalette.setSpacing(5);
    myShapePalette.getChildren().add(new Label("Turtle Shapes"));
    myContents.getChildren().addAll(myColorPalette, myShapePalette);
    this.setContent(myContents);
    myColorPaletteEntries = new ArrayList<>();
    myContents.setSpacing(20);
  }

  public void setColors(ObservableList<Color> colors) {
    for (int i = 0; i < colors.size(); i++) {
      if (myColorPaletteEntries.size() <= i) {
        ColorPalatteEntry entry = new ColorPalatteEntry(i, colors.get(i));
        myColorPaletteEntries.add(entry);
        myColorPalette.getChildren().add(entry);
      } else {
        myColorPaletteEntries.get(i).setColor(colors.get(i));
      }
    }
  }

  public void setShapes(Map<Integer, String> shapeMap) {
    for (int i = 0; i < shapeMap.size(); i++) {
      myShapePalette.getChildren().add(new TurtleShapeEntry(i, shapeMap.get(i)));
    }
  }
}
