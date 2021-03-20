package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.view.Selector;

/**
 * Selector for turtle image
 *
 * @author David Li
 */
public class TurtleSelector extends MenuBarSelector<String> {

  /**
   * Main constructor
   */
  public TurtleSelector() {
    super("Turtle Image", "TurtleSelector", "TurtleSelectorComboBox", new ComboBox<>());

    ComboBox<String> comboBox = (ComboBox<String>) getComboBoxBase();
    comboBox.getItems().addAll("Default", "Realistic");
    comboBox.getSelectionModel().selectFirst();
  }
}
