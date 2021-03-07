package slogo.view;

import java.util.function.Consumer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TurtleSelector extends VBox implements Selector<String> {

  private final ComboBox<String> myComboBox;

  public TurtleSelector() {
    Label myLabel = new Label("Turtle Image");
    myComboBox = new ComboBox<>();
    myComboBox.getItems().addAll("Default");
    myComboBox.getSelectionModel().selectFirst();

    this.getChildren().addAll(myLabel, myComboBox);
  }

  @Override
  public void setUpdateAction(Consumer<String> response) {
    myComboBox.setOnAction(e -> response.accept(myComboBox.getValue()));
  }
}
