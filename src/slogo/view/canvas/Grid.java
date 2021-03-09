package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.layout.StackPane;
import slogo.view.SelectorTarget;

public class Grid extends StackPane implements SelectorTarget<String> {

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
  }

  public void updateTurtleView(double x, double y, double heading) {
    myTurtleView.setTranslateX(x);
    myTurtleView.setTranslateY(y);
    myTurtleView.setRotate(heading);
  }
}
