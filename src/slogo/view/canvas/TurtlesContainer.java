package slogo.view.canvas;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Coordinates;

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
   * @return The new TurtleView created
   */
  public TurtleView createTurtle(Coordinates coordinates) {
    TurtleView newTurtle = new TurtleView(coordinates);
    myTurtleViews.add(newTurtle);
    return newTurtle;
  }

  /**
   * @param id Id of TurtleView being queried
   * @return TurtleView requested
   */
  public TurtleView get(int id) {
    return myTurtleViews.get(id - 1);
  }
}
