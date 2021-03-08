package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.view.Selector;

public class GridSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  public GridSelector() {
    this.setId("GridSelector");
    this.getStyleClass().add("selector");
    Label myLabel = new Label("Grid Settings");
    myComboBox = new ComboBox<>();
    myComboBox.getItems().addAll("None", "Axis");
    myComboBox.getSelectionModel().selectFirst();

    this.getChildren().addAll(myLabel, myComboBox);
  }

  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
