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
public class LanguageSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  /**
   * Main constructor
   */
  public LanguageSelector() {
    this.setId("LanguageSelector");
    this.getStyleClass().add("selector");
    Label label = new Label("Language");
    myComboBox = new ComboBox<>();
    myComboBox.getItems()
        .addAll("English",
            "Chinese",
            "French",
            "German",
            "Italian",
            "Portuguese",
            "Russian",
            "Spanish",
            "Urdu");
    myComboBox.getSelectionModel().selectFirst();

    this.getChildren().addAll(label, myComboBox);
  }

  /**
   * Passes the selected language to the consumer
   */
  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
