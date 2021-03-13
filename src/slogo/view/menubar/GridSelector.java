package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import slogo.view.Selector;

/**
 * Selector for color of the canvas background
 *
 * @author David Li
 */
public class GridSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  /**
   * Main constructor
   */
  public GridSelector() {
    this.setId("GridSelector");
    this.getStyleClass().add("selector");
    Label label = new Label("Grid Settings");
    myComboBox = new ComboBox<>();
    myComboBox.setId("GridSelectorComboBox");
    myComboBox.getItems().addAll("None", "Axis");
    myComboBox.getSelectionModel().selectFirst();

    this.getChildren().addAll(label, myComboBox);
  }

  /**
   * Passes the selected grid type to the consumer
   */
  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
