package slogo.view.info;

import javafx.scene.control.TabPane;

public class InfoDisplay extends TabPane {

  private final TurtlesTab myTurtlesTab;
  private final VariablesTab myVariablesTab;
  private final CommandsTab myCommandsTab;
  private final PalettesTab myPalettesTab;

  public InfoDisplay() {
    myTurtlesTab = new TurtlesTab();
    myVariablesTab = new VariablesTab();
    myCommandsTab = new CommandsTab();
    myPalettesTab = new PalettesTab();
  }

}
