package slogo.view.menubar;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.controller.Controller;
import slogo.controller.Lexer;
import slogo.view.Selector;

/**
 * Selector for color of the canvas background
 *
 * @author David Li
 */
public class LanguageSelector extends MenuBarSelector<String> {

  private static final String LANGUAGE_OPTIONS_FILENAME = "LangaugeOptions";

  /**
   * Main constructor
   */
  public LanguageSelector() {
    super("Language", "LanguageSelector", "LanguageComboBox", new ComboBox<>());
    ComboBox<String> comboBox = (ComboBox<String>) getComboBoxBase();

    ResourceBundle langResources = ResourceBundle.getBundle(Lexer.RESOURCES_PACKAGE + LANGUAGE_OPTIONS_FILENAME);
    List<String> languages = new ArrayList<>(langResources.keySet());
    languages.stream().sorted().forEach(language -> comboBox.getItems().add(language));
    comboBox.getSelectionModel().select(Controller.DEFAULT_LANGUAGE);
  }
}
