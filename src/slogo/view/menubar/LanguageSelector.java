package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.view.Selector;

/**
 * Selector for color of the canvas background
 *
 * @author David Li
 */
public class LanguageSelector extends MenuBarSelector<String> {

  /**
   * Main constructor
   */
  public LanguageSelector() {
    super("Language", "LanguageSelector", "LanguageComboBox", new ComboBox<>());

    ComboBox<String> comboBox = (ComboBox<String>) getComboBoxBase();
    comboBox.getItems()
        .addAll("English",
            "Chinese",
            "French",
            "German",
            "Italian",
            "Portuguese",
            "Russian",
            "Spanish",
            "Urdu");
    comboBox.getSelectionModel().selectFirst();
  }
}
