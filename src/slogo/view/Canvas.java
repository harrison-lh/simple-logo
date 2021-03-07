package slogo.view;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Canvas extends StackPane implements SelectorTarget<Color> {

  public Canvas() {
    this.setId("Canvas");
    this.getStyleClass().add("box");
    this.getChildren().add(new Label("canvas"));
  }

  @Override
  public Consumer<Color> updateAction() {
    return color -> this
        .setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }
}
