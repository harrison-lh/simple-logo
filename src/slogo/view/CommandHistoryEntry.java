package slogo.view;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class CommandHistoryEntry extends StackPane {

  private final String myCommand;
  private final Label myCommandLabel;
  private final Rectangle myRectangle;

  public CommandHistoryEntry(String command, Consumer<String> consumer) {
    myCommand = command;
    String reformattedCommand = "> " + command.replace("\n", "\n  ");
    myCommandLabel = new Label(reformattedCommand);
    myCommandLabel.getStyleClass().add("monospace-font");
    myRectangle = new Rectangle();
    initializeRectangle(consumer);
    this.getChildren().addAll(myCommandLabel, myRectangle);
    StackPane.setAlignment(myCommandLabel, Pos.TOP_LEFT);
  }

  public Rectangle getRectangle() {
    return myRectangle;
  }

  private void initializeRectangle(Consumer<String> consumer) {
    myRectangle.heightProperty().bind(myCommandLabel.heightProperty());
    myRectangle.setOpacity(0);
    myRectangle.setOnMouseEntered(event -> myRectangle.setOpacity(0.2));
    myRectangle.setOnMouseExited(event -> myRectangle.setOpacity(0));
    // TODO: Execute command
    myRectangle.setOnMouseClicked(event -> consumer.accept(myCommand));
  }

}
