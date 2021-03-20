package slogo.view.info;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Display panel for information about the turtles, variables, commands,
 * and palettes.
 *
 * @author David Li
 */
public class InfoDisplay extends TabPane {

  private static final double WIDTH = 280;

  private final TurtlesBox myTurtlesBox;
  private final VariablesBox myVariablesBox;
  private final CommandsBox myCommandsBox;
  private final PalettesBox myPalettesBox;

  /**
   * Main constructor
   */
  public InfoDisplay() {
    this.setId("InfoDisplay");

    this.setPrefWidth(WIDTH);

    myTurtlesBox = new TurtlesBox();
    myVariablesBox = new VariablesBox();
    myCommandsBox = new CommandsBox();
    myPalettesBox = new PalettesBox();

    Tab turtlesTab = new InfoTab("Turtles", myTurtlesBox);
    Tab variablesTab = new InfoTab("Variables", myVariablesBox);
    Tab commandsTab = new InfoTab("Commands", myCommandsBox);
    Tab palettesTab = new InfoTab("Palettes", myPalettesBox);

    this.getTabs().addAll(turtlesTab, variablesTab, commandsTab, palettesTab);
  }

  public VariablesBox getVariablesBox() {
    return myVariablesBox;
  }

  public CommandsBox getCommandsBox() {
    return myCommandsBox;
  }
}
