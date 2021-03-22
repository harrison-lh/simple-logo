package slogo.view.canvas;

import java.util.ArrayList;
import java.util.List;
import slogo.controller.TurtleProperties;

/**
 * Container for all TurtleViews in the TurtleCanvas
 *
 * @author David Li
 */
public class TurtlesContainer {

  private final List<TurtleView> myTurtleViews;

  /**
   * Main Constructor
   */
  public TurtlesContainer() {
    myTurtleViews = new ArrayList<>();
  }

  /**
   * Creates a new TurtleView
   *
   * @return The new TurtleView created
   */
  public TurtleView createTurtle(TurtleProperties turtleProperties) {
    TurtleView newTurtle = new TurtleView(turtleProperties);
    myTurtleViews.add(newTurtle);
    return newTurtle;
  }

  public void setTurtleShapes(String shape) {
    for (TurtleView turtleView : myTurtleViews) {
      turtleView.changeTurtleImage(shape);
    }
  }

  /**
   * @param id Id of TurtleView being queried
   * @return TurtleView requested
   */
  public TurtleView get(int id) {
    return myTurtleViews.get(id - 1);
  }
}
