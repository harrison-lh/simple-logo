package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import slogo.view.SelectorTarget;

/**
 * Holds the turtle canvas in the main part of the application window Responsible for maintaining
 * the aspect ratio of the turtle canvas
 *
 * @author David Li
 */
public class CanvasHolder extends AnchorPane implements SelectorTarget<Color> {

  private static final double CANVAS_MARGIN = 6;

  private final TurtleCanvas myTurtleCanvas;

  /**
   * Main Constructor
   */
  public CanvasHolder() {
    this.setId("Canvas");

    myTurtleCanvas = new TurtleCanvas();

    this.getChildren().addAll(myTurtleCanvas);
    this.resizeElements();
  }

  /**
   * Changes background color of the turtle canvas when a new background color is selected
   */
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

  /**
   * Resizes the turtle canvas when the window size changes
   */
  public void resizeElements() {
    boolean constrainedByWidth = (this.getWidth() / this.getHeight() < TurtleCanvas.ASPECT_RATIO);
    double widthMargin;
    double heightMargin;
    if (constrainedByWidth) {
      widthMargin = CANVAS_MARGIN;
      heightMargin = CANVAS_MARGIN
          + (this.getHeight() - (1 / TurtleCanvas.ASPECT_RATIO) * this.getWidth()) / 2;
    } else {
      widthMargin =
          CANVAS_MARGIN + (this.getWidth() - TurtleCanvas.ASPECT_RATIO * this.getHeight()) / 2;
      heightMargin = CANVAS_MARGIN;
    }
    AnchorPane.setTopAnchor(myTurtleCanvas, heightMargin);
    AnchorPane.setBottomAnchor(myTurtleCanvas, heightMargin);
    AnchorPane.setLeftAnchor(myTurtleCanvas, widthMargin);
    AnchorPane.setRightAnchor(myTurtleCanvas, widthMargin);

    myTurtleCanvas.resizeElements();
  }
}
