package slogo.view.menubar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import slogo.controller.Controller;
import slogo.controller.Lexer;
import slogo.view.LanguageConsumer;

/**
 * Selector for the language
 *
 * @author David Li
 */
public class LanguageSelector extends MenuBarSelector<String> {

  private static final String LANGUAGE_OPTIONS_FILENAME = "LangaugeOptions";

  private final Collection<Consumer<String>> myLanguageConsumers;

  /**
   * Main constructor
   */
  public LanguageSelector() {
    super("Language", "LanguageSelector", "LanguageComboBox", new ComboBox<>());
    ComboBox<String> comboBox = (ComboBox<String>) getComboBoxBase();

    ResourceBundle langResources = ResourceBundle
        .getBundle(Lexer.RESOURCES_PACKAGE + LANGUAGE_OPTIONS_FILENAME);
    List<String> languages = new ArrayList<>(langResources.keySet());
    languages.stream().sorted().forEach(language -> comboBox.getItems().add(language));
    comboBox.getSelectionModel().select(Controller.DEFAULT_LANGUAGE);

    myLanguageConsumers = new HashSet<>();
  }

  public void addLanguageConsumers(LanguageConsumer... consumers) {
    for (LanguageConsumer consumer : consumers) {
      myLanguageConsumers.add(consumer.languageConsumer());
    }
  }

  @Override
  public void setUpdateAction(Consumer<String> response) {
    getComboBoxBase().setOnAction(e -> {
      response.accept(getComboBoxBase().getValue());
      myLanguageConsumers.forEach(consumer -> consumer.accept(getComboBoxBase().getValue()));
    });
  }
}
