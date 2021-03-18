package slogo.view;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public abstract class ClickableEntry<T> extends StackPane {

  private Label myLabel;
  private final Rectangle myRectangle;

  public ClickableEntry(Consumer<T> consumer) {
    myLabel = new Label();
    myRectangle = new Rectangle();
    initializeRectangle(consumer);
    StackPane.setAlignment(myLabel, Pos.TOP_LEFT);
    this.getChildren().addAll(myLabel, myRectangle);
  }
  public ClickableEntry(String text, Consumer<T> consumer) {
    this(consumer);
    this.setText(text);
  }

  public Rectangle getRectangle() {
    return myRectangle;
  }

  protected abstract void onClick(Consumer<T> consumer);

  protected void setText(String text) {
    myLabel.setText(text);
  }

  private void initializeRectangle(Consumer<T> consumer) {
    myRectangle.heightProperty().bind(myLabel.heightProperty());
    myRectangle.setOpacity(0);
    myRectangle.setOnMouseEntered(event -> {
      myRectangle.setOpacity(0.2);
      getScene().setCursor(Cursor.HAND);
    });
    myRectangle.setOnMouseExited(event -> {
      myRectangle.setOpacity(0);
      getScene().setCursor(Cursor.DEFAULT);
    });
    myRectangle.setOnMouseClicked(event -> onClick(consumer));
  }


}
