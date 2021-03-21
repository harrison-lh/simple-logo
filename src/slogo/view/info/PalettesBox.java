package slogo.view.info;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
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
  private List<ColorPalatteEntry> myColorPaletteEntries;

  /**
   * Main constructor
   */
  public PalettesBox() {
    this.setId("PalettesBox");
    this.getStyleClass().add("info-box");
    myContents = new VBox();
    this.setContent(myContents);
    myColorPaletteEntries = new ArrayList<>();
    myContents.setSpacing(5);
  }

  public void setColors(ObservableList<Color> colors) {
    for (int i = 0; i < colors.size(); i++) {
      if (myColorPaletteEntries.size() <= i) {
        ColorPalatteEntry entry = new ColorPalatteEntry(i, colors.get(i));
        myColorPaletteEntries.add(entry);
        myContents.getChildren().add(entry);
      }
      else {
        myColorPaletteEntries.get(i).setColor(colors.get(i));
      }
    }
  }
}
