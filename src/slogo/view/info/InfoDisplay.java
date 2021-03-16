package slogo.view.info;

import javafx.scene.control.TabPane;

public class InfoDisplay extends TabPane {

  private static final double WIDTH = 280;

  private final TurtlesTab myTurtlesTab;
  private final VariablesTab myVariablesTab;
  private final CommandsTab myCommandsTab;
  private final PalettesTab myPalettesTab;

  public InfoDisplay() {
    this.setId("InfoDisplay");

    this.setPrefWidth(WIDTH);

    myTurtlesTab = new TurtlesTab();
    myVariablesTab = new VariablesTab();
    myCommandsTab = new CommandsTab();
    myPalettesTab = new PalettesTab();

    this.getTabs().addAll(myTurtlesTab, myVariablesTab, myCommandsTab, myPalettesTab);
  }

  public VariablesBox getVariablesBox() {
    return myVariablesTab.getVariablesBox();
  }

  public CommandsBox getCommandsBox() {
    return myCommandsTab.getCommandsBox();
  }
}
