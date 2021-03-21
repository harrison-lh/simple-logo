package slogo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import slogo.view.Pen;

/**
 * The Turtle is the object that commands of forward and right are put upon, and it contains the
 * fundamental variables of where the turtle is placed and facing.
 *
 * @author Harrison Huang
 * @author Cole Spector
 * @author David Li
 */
public class Turtle {

  private Coordinates coordinates;
  private Variables vars;
  private PropertyChangeListener turtleListener;
  private BooleanProperty isVisibleProperty;
  private BooleanProperty penActiveProperty;
  private int id;

  /**
   * Primary constructor for Turtle, which takes initial coordinates, a pen, and a pair of listeners
   * and constructs a Turtle.
   *
   * @param coordinates The coordinate system that this Turtle will operate in
   * @param turtleListener A listener that will report changes in this Turtle's location and other
   *                       properties to the View TODO: CHANGE THIS!
   * @param variablesListener A listener that will report changes in Variables TODO: CHANGE THIS TOO!
   */
  public Turtle(int id, Coordinates coordinates, PropertyChangeListener turtleListener,
      PropertyChangeListener variablesListener) {
    this.id = id;
    this.coordinates = coordinates;
    this.vars = new Variables(variablesListener);
    this.turtleListener = turtleListener;
    isVisibleProperty = new SimpleBooleanProperty(true);
    penActiveProperty = new SimpleBooleanProperty(true);
  }

  /**
   * Constructor with no listeners attached
   */
  public Turtle(int id, Coordinates coordinates) {
    this(id, coordinates, evt -> {
    }, evt -> {
    });
  }

  /**
   * Creates a new Turtle object given parameters of a starting position and heading.
   *
   * @param id      id number
   * @param x       X coordinate
   * @param y       Y coordinate
   * @param heading direction turtle is facing relative to positive x-axis
   */

  public Turtle(int id, Coordinates coordinates, double x, double y, double heading) {
    this.id = id;
    this.coordinates = coordinates;
    coordinates.setXY(x, y);
    coordinates.setHeading(heading);
  }

  public int getId() {
    return id;
  }

  public Variables getVars() {
    return vars;
  }

  /**
   * Moves the turtle forward a certain number of pixels (or backward, for a negative number).
   *
   * @param pixels number of pixels the turtle will move forward
   */
  public void forward(double pixels) {
    double xPos = getX();
    double yPos = getY();

    xPos += pixels * Math.cos(Math.toRadians(getHeading()));
    yPos += pixels * Math.sin(Math.toRadians(getHeading()));

    setPosition(xPos, yPos);
  }

  /**
   * Turns the turtle to the right for a certain number of degrees.
   *
   * @param degrees number of degrees the turtle will move clockwise
   */
  public void right(double degrees) {
    setHeading(getHeading() - degrees);
  }

  /**
   * Getter method for obtaining the x-coordinate of the turtle.
   *
   * @return double of turtle's x-coordinate
   */
  public double getX() {
    return coordinates.getX();
  }

  /**
   * Getter method for obtaining the y-coordinate of the turtle.
   *
   * @return double of turtle's y-coordinate
   */
  public double getY() {
    return coordinates.getY();
  }

  /**
   * Sets x and y coordinates of the turtle.
   *
   * @param x New x-coordinate
   * @param y New y-coordinate
   */
  public void setPosition(double x, double y) {
    coordinates.setXY(x, y);
  }

  /**
   * Getter method for obtaining the heading of the turtle in degrees counterclockwise from the
   * x-axis.
   *
   * @return heading of the turtle in degrees
   */

  public double getHeading() {
    return coordinates.getHeading();
  }

  /**
   * Setter method for the heading of the turtle. Automatically recalculates to a number between 0
   * and 360 degrees.
   *
   * @param heading The new heading of the object in degrees.
   */

  public void setHeading(double heading) {
    coordinates.setHeading(heading);
  }

  /**
   * Returns if the turtle is visible on screen.
   *
   * @return boolean whether the turtle is visible
   */
  public boolean isVisible() {
    return isVisibleProperty.get();
  }

  /**
   * Sets the visibility of the turtle.
   *
   * @param isVisible boolean whether the turtle is visible
   */
  public void setVisible(boolean isVisible) {
    this.isVisibleProperty.set(isVisible);
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
   * Makes the pen inactive.
   */
  public void liftPen() {
    penActiveProperty.set(false);
  }

  /**
   * Makes the pen active.
   */
  public void placePen() {
    penActiveProperty.set(true);
  }

  /**
   * Returns boolean for if the pen is down.
   *
   * @return The boolean if the pen is down
   */
  public boolean isPenActive() {
    return penActiveProperty.get();
  }

  /**
   * Returns the property of the pen being active or not. Able to be listened to.
   *
   * @return The BooleanProperty for pen up/down
   */
  public BooleanProperty penActiveProperty() {
    return penActiveProperty;
  }

  /**
   * Clears the screen of the Turtle's lines.
   */
  public void clearScreen() {
    turtleListener.propertyChange(new PropertyChangeEvent(this, "CLEAR", null, null));
  }

  /**
   * Getter method for the coordinates object of the turtle.
   *
   * @return The coordinates object
   */
  public Coordinates getCoordinates() {
    return coordinates;
  }
}
