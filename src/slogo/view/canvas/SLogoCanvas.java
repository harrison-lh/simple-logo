package slogo.view.canvas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import slogo.view.SelectorTarget;

public class SLogoCanvas extends AnchorPane implements SelectorTarget<Color>,
    PropertyChangeListener {

  public static final double CANVAS_MARGIN = 6;
  public static final double GRID_ASPECT_RATIO = 4.0 / 3;

  private final Grid myGrid;

  public SLogoCanvas() {
    this.setId("Canvas");

    myGrid = new Grid();

    this.getChildren().addAll(myGrid);
    this.resizeElements();
  }

  @Override
  public Consumer<Color> updateAction() {
    return color -> myGrid
        .setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public Grid getGrid() {
    return myGrid;
  }

  public TurtleView getTurtleView() {
    return myGrid.getTurtleView();
  }

  public void resizeElements() {
    boolean constrainedByWidth = (this.getWidth() / this.getHeight() < GRID_ASPECT_RATIO);
    double widthMargin;
    double heightMargin;
    if (constrainedByWidth) {
      widthMargin = CANVAS_MARGIN;
      heightMargin = CANVAS_MARGIN + (this.getHeight() - (1 / GRID_ASPECT_RATIO) * this.getWidth()) / 2;
    }
    else {
      widthMargin = CANVAS_MARGIN + (this.getWidth() - GRID_ASPECT_RATIO * this.getHeight()) / 2;
      heightMargin = CANVAS_MARGIN;
    }
    AnchorPane.setTopAnchor(myGrid, heightMargin);
    AnchorPane.setBottomAnchor(myGrid, heightMargin);
    AnchorPane.setLeftAnchor(myGrid, widthMargin);
    AnchorPane.setRightAnchor(myGrid, widthMargin);

    myGrid.resizeElements();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("X")) {
      myGrid.setTurtleX((Integer) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("Y")) {
      myGrid.setTurtleY((Integer) evt.getNewValue());
    }
    else if (evt.getPropertyName().equals("HEADING")) {
      myGrid.setTurtleHeading((Integer) evt.getNewValue());
    }
  }
}
