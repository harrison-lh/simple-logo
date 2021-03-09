package slogo.view.menubar;

import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.view.Selector;

public class TurtleSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  public TurtleSelector() {
    this.setId("TurtleSelector");
    this.getStyleClass().add("selector");
    Label myLabel = new Label("Turtle Image");
    myComboBox = new ComboBox<>();
    myComboBox.setId("TurtleSelectorComboBox");
    myComboBox.getItems().addAll("Default", "Realistic");
    myComboBox.getSelectionModel().selectFirst();

    this.getChildren().addAll(myLabel, myComboBox);
  }

  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
