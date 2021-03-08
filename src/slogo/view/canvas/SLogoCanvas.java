package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import slogo.view.SelectorTarget;

public class SLogoCanvas extends AnchorPane implements SelectorTarget<Color> {

  private final Grid myGrid;

  public SLogoCanvas() {
    this.setId("Canvas");
    this.getStyleClass().add("box");

    myGrid = new Grid();

    this.getChildren().addAll(myGrid);

    this.resizeElements();
  }

  @Override
  public Consumer<Color> updateAction() {
    return color -> this
        .setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public Grid getGrid() {
    return myGrid;
  }

  public TurtleView getTurtleView() {
    return myGrid.getTurtleView();
  }

  public void resizeElements() {
    myGrid.resizeElements();

    AnchorPane.setTopAnchor(myGrid, 10.0);
    AnchorPane.setBottomAnchor(myGrid, 10.0);
    AnchorPane.setLeftAnchor(myGrid, 10.0);
    AnchorPane.setRightAnchor(myGrid, 10.0);
  }
}
