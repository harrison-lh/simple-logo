package slogo.controller;

import javafx.beans.property.BooleanProperty;
import slogo.model.Coordinates;
import slogo.model.Turtle;

/**
 * TurtleProperties contains all of the properties that the view needs to listen for from the
 * backend, namely, the coordinates of the turtle, whether the turtle is visible, and whether its
 * pen is active. This class bundles all of the properties together to avoid passing the Turtle
 * class directly to view and also to avoid passing multiple property parameters to view.
 *
 * @author Harrison Huang
 */

public class TurtleProperties {

  private final Coordinates coordinates;
  private final BooleanProperty isVisibleProperty;
  private final BooleanProperty penActiveProperty;

  /**
   * Constructor of TurtleProperties. Takes in a turtle to get all of its properties.
   *
   * @param turtle The turtle to extract properties
   */
  public TurtleProperties(Turtle turtle) {
    coordinates = turtle.getCoordinates();
    isVisibleProperty = turtle.visibleProperty();
    penActiveProperty = turtle.penActiveProperty();
  }

  /**
   * Getter method for the coordinates object of the turtle.
   *
   * @return The coordinates object
   */
  public Coordinates getCoordinates() {
    return coordinates;
  }

  /**
   * Returns the property of the visibility of the Turtle. Able to be listened to.
   *
   * @return The BooleanProperty for show/hide turtle
   */
  public BooleanProperty visibleProperty() {
    return isVisibleProperty;
  }

  /**
   * Returns the property of the pen being active or not. Able to be listened to.
   *
   * @return The BooleanProperty for pen up/down
   */
  public BooleanProperty penActiveProperty() {
    return penActiveProperty;
  }

}
