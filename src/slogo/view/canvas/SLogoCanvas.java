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
import slogo.model.Coordinates;
import slogo.view.SelectorTarget;

public class SLogoCanvas extends AnchorPane implements SelectorTarget<Color> {

  public static final double CANVAS_MARGIN = 6;
  public static final double GRID_ASPECT_RATIO = 4.0 / 3;

  private final TurtleCanvas myTurtleCanvas;

  public SLogoCanvas() {
    this.setId("Canvas");

    myTurtleCanvas = new TurtleCanvas();

    this.getChildren().addAll(myTurtleCanvas);
    this.resizeElements();
  }

  @Override
  public Consumer<Color> updateAction() {
    return color -> myTurtleCanvas
        .setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public TurtleCanvas getTurtleCanvas() {
    return myTurtleCanvas;
  }

  public TurtleView getTurtleView() {
    return myTurtleCanvas.getTurtleView();
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
    AnchorPane.setTopAnchor(myTurtleCanvas, heightMargin);
    AnchorPane.setBottomAnchor(myTurtleCanvas, heightMargin);
    AnchorPane.setLeftAnchor(myTurtleCanvas, widthMargin);
    AnchorPane.setRightAnchor(myTurtleCanvas, widthMargin);

    myTurtleCanvas.resizeElements();
  }
}
