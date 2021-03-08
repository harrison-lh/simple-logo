package slogo.view.canvas;

import java.util.TreeMap;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import slogo.view.SelectorTarget;

public class SLogoCanvas extends StackPane implements SelectorTarget<Color> {

  private final Grid myGrid;
  private final TurtleView myTurtleView;

  public SLogoCanvas() {
    this.setId("Canvas");
    this.getStyleClass().add("box");

    myGrid = new Grid();
    myGrid.prefWidthProperty().bind(this.widthProperty());
    myGrid.prefHeightProperty().bind(this.heightProperty());

    myTurtleView = new TurtleView();

    this.getChildren().addAll(myGrid, myTurtleView);
  }

  @Override
  public Consumer<Color> updateAction() {
    return color -> this
        .setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public Grid getGrid() {
    return myGrid;
  }
}
