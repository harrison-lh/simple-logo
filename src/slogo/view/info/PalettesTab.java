package slogo.view.info;

import javafx.scene.control.Tab;

public class PalettesTab extends InfoTab {

  private final PalettesBox myPalettesBox;

  public PalettesTab() {
    super("Palettes");
    myPalettesBox = new PalettesBox();
    this.setContent(myPalettesBox);
  }

}
