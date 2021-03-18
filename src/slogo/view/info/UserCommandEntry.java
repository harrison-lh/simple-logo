package slogo.view.info;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class UserCommandEntry extends StackPane {

  private final String myCommand;
  private final Label myCommandLabel;
  private final Rectangle myRectangle;

  public UserCommandEntry(String command, Consumer<String> consumer) {
    myCommand = command;
    myCommandLabel = new Label(command);
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
    myRectangle.setOnMouseEntered(event -> {
      myRectangle.setOpacity(0.2);
      getScene().setCursor(Cursor.HAND);
    });
    myRectangle.setOnMouseExited(event -> {
      myRectangle.setOpacity(0);
      getScene().setCursor(Cursor.DEFAULT);
    });
    // TODO: figure out way to input parameters
    myRectangle.setOnMouseClicked(event -> consumer.accept(myCommand));
  }

}
