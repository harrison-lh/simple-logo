package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import slogo.view.SelectorTarget;

/**
 * Holds the turtle canvas in the main part of the application window Responsible for maintaining
 * the aspect ratio of the turtle canvas
 *
 * @author David Li
 */
public class CanvasHolder extends StackPane implements SelectorTarget<Color> {

  private static final double CANVAS_MARGIN = 6;

  private final TurtleCanvas myTurtleCanvas;

  /**
   * Main Constructor
   */
  public CanvasHolder() {
    this.setId("CanvasHolder");

    myTurtleCanvas = new TurtleCanvas();

    this.getChildren().addAll(myTurtleCanvas);
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
}
