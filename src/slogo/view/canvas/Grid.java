package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.layout.StackPane;
import slogo.view.SelectorTarget;

public class Grid extends StackPane implements SelectorTarget<String> {

  public static final double DEFAULT_GRID_WIDTH = 600;
  public static final double DEFAULT_GRID_HEIGHT = DEFAULT_GRID_WIDTH / SLogoCanvas.GRID_ASPECT_RATIO;

  private final GridLines myGridLines;
  private final TurtleView myTurtleView;

  public Grid() {
    this.setId("Grid");

    myGridLines = new GridLines();
    myTurtleView = new TurtleView();
    myGridLines.changeGridType("None");

    this.getChildren().addAll(myGridLines, myTurtleView);
  }

  public TurtleView getTurtleView() {
    return myTurtleView;
  }

  @Override
  public Consumer<String> updateAction() {
    return myGridLines::changeGridType;
  }

  public void resizeElements() {
    myGridLines.resize();
    myTurtleView.setTranslateX(convertXCoordinate(myTurtleView.getXCoordinate()));
    myTurtleView.setTranslateY(convertYCoordinate(myTurtleView.getYCoordinate()));
  }

  public void updateTurtleView(double x, double y, double heading) {
    myTurtleView.setLocation(x, y);
    myTurtleView.setTranslateX(convertXCoordinate(x));
    myTurtleView.setTranslateY(convertYCoordinate(y));
    myTurtleView.setHeading(heading);
    myTurtleView.setRotate(heading);
  }

  private double convertXCoordinate(double x) {
    return x * this.getWidth() / DEFAULT_GRID_WIDTH;
  }

  private double convertYCoordinate(double y) {
    return -1 * y * this.getHeight() / DEFAULT_GRID_HEIGHT;
  }
}
