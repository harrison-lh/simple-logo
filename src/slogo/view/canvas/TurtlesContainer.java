package slogo.view.canvas;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Coordinates;

public class TurtlesContainer {

  private final List<TurtleView> myTurtleViews;

  public TurtlesContainer() {
    myTurtleViews = new ArrayList<>();
  }

  public TurtleView createTurtle(Coordinates coordinates) {
    TurtleView newTurtle = new TurtleView(coordinates);
    myTurtleViews.add(newTurtle);
    return newTurtle;
  }

  public TurtleView get(int id) {
    return myTurtleViews.get(id - 1);
  }
}
