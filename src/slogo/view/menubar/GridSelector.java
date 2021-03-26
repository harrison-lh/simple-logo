package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ComboBox;

/**
 * Selector for type of grid (axis or no axis)
 *
 * @author David Li
 */
public class GridSelector extends MenuBarSelector<String> {

  /**
   * Main constructor
   */
  public GridSelector() {
    super("Grid Settings", "GridSelector", "GridSelectorComboBox", new ComboBox<>());

    ComboBox<String> comboBox = (ComboBox<String>) getComboBoxBase();
    comboBox.setId("GridSelectorComboBox");
    comboBox.getItems().addAll("None", "Axis");
    comboBox.getSelectionModel().selectFirst();
  }

  /**
   * Passes the combo box action to pass the selected value to the consumer
   * @param response consumer of combo box value
   */
  @Override
  public void setUpdateAction(Consumer<String> response) {
    getComboBoxBase().setOnAction(e -> {
      response.accept(getComboBoxBase().getValue());
    });
  }
}
