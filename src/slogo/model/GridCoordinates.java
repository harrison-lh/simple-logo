package slogo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The GridCoordinates class is an implementation of the Coordinates abstract class. It implements
 * Coordinates on the Cartesian plane. GridCoordinates contains a StringProperty that allows for
 * other classes to be notified when a value is changed.
 *
 * @author Harrison Huang
 */
public class GridCoordinates extends Coordinates {

  private final StringProperty positionString;

  /**
   * Constructor of GridCoordinates given input parameters of x-coordinate,
   * y-coordinate, and heading.
   *
   * @param x       The initial x-coordinate
   * @param y       The initial y-coordinate
   * @param heading The heading in degrees
   */
  public GridCoordinates(double x, double y, double heading) {
    super(x, y, heading);
    positionString = new SimpleStringProperty(toString());
  }

  /**
   * Default constructor of GridCoordinates.
   */
  public GridCoordinates() {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_HEADING);
  }

  /**
   * Constructor of GridCoordinates using another Coordinates object. Instantiates with the same
   * values as the input object.
   *
   * @param coordinates The Coordinates object from which to copy values
   */
  public GridCoordinates(Coordinates coordinates) {
    this(coordinates.getX(), coordinates.getY(), coordinates.getHeading());
  }

  /**
   * Gets the x-coordinate of the object.
   *
   * @return The current x-coordinate of the object
   */
  @Override
  public double getX() {
    return xPos;
  }

  /**
   * Sets the (x, y) position of the object, which also updates the string.
   *
   * @param x The new x-coordinate of the object
   * @param y The new y-coordinate of the object
   */
  @Override
  public void setXY(double x, double y) {
    xPos = x;
    yPos = y;
    updateStringProperty();
  }

  /**
   * Gets the y-coordinate of the object.
   *
   * @return The current y-coordinate of the object
   */
  @Override
  public double getY() {
    return yPos;
  }

  /**
   * Gets the current heading of the object in degrees.
   *
   * @return heading The current heading of the object in degrees.
   */
  @Override
  public double getHeading() {
    return heading;
  }

  /**
   * Sets the heading of the object in degrees. Also automatically recalculates in order to keep
   * the heading between 0 and 360 degrees.
   *
   * @param heading The new heading of the object in degrees.
   */
  @Override
  public void setHeading(double heading) {
    if (heading > MAX_DEGREES || heading < 0) {
      heading = heading % MAX_DEGREES;
      if (heading < 0) {
        heading += MAX_DEGREES;
      }
    }
    this.heading = heading;
    updateStringProperty();
  }

  /**
   * The property of the GridCoordinates object as a string.
   *
   * @return The StringProperty of the GridCoordinates object
   */
  @Override
  public StringProperty stringProperty() {
    return positionString;
  }

  private void updateStringProperty() {
    positionString.set(toString());
  }

}
