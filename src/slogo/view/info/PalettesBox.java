package slogo.view.info;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Display box for palettes
 *
 * @author David Li
 */
public class PalettesBox extends ScrollPane {

  private final VBox myContents;
  /**
   * Main constructor
   */
  public PalettesBox() {
    this.setId("PalettesBox");
    this.getStyleClass().add("info-box");
    myContents = new VBox();
    this.setContent(myContents);
  }
}
