package slogo.view;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Clickable item that carries out some action when clicked.
 * @param <T> Type of variable that gets passed to the consumer
 *
 * @author David Li
 */
public abstract class ClickableEntry<T> extends StackPane {

  private final Label myLabel;
  private final Rectangle myRectangle;

  /**
   * Main constructor
   * @param consumer Consumer of clickable action
   */
  public ClickableEntry(Consumer<T> consumer) {
    myLabel = new Label();
    myRectangle = new Rectangle();
    initializeRectangle(consumer);
    StackPane.setAlignment(myLabel, Pos.TOP_LEFT);
    this.getChildren().addAll(myLabel, myRectangle);
  }

  /**
   * Constructor with initial text
   * @param text Text of label of the entry
   */
  public ClickableEntry(String text, Consumer<T> consumer) {
    this(consumer);
    this.setText(text);
  }

  public Rectangle getRectangle() {
    return myRectangle;
  }

  protected abstract void onClick(Consumer<T> consumer);

  protected Label getLabel() {
    return myLabel;
  }

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
