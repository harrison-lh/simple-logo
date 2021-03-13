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
public class TurtleSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  /**
   * Main constructor
   */
  public TurtleSelector() {
    this.setId("TurtleSelector");
    this.getStyleClass().add("selector");
    Label label = new Label("Turtle Image");
    myComboBox = new ComboBox<>();
    myComboBox.setId("TurtleSelectorComboBox");
    myComboBox.getItems().addAll("Default", "Realistic");
    myComboBox.getSelectionModel().selectFirst();

    this.getChildren().addAll(label, myComboBox);
  }

  /**
   * Passes the selected turtle image to the consumer
   */
  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
