package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.view.Selector;

public class LanguageSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  public LanguageSelector() {
    this.getStyleClass().add("selector");
    Label myLabel = new Label("Language");
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

    this.getChildren().addAll(myLabel, myComboBox);
  }

  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
