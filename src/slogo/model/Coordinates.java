package slogo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * An interface that defines the properties of an object that has Coordinates.
 * <p>
 * Coordinates allow the View to determine where a Turtle or other Model object is in space, as well
 * as its heading and vice versa.
 *
 * @author Marc Chmielewski
 * @author Cole Spector
 */
public abstract class Coordinates extends SimpleObjectProperty<Coordinates> {

  protected static final double MAX_DEGREES = 360;
  protected static final double DEFAULT_X = 0;
  protected static final double DEFAULT_Y = 0;
  protected static final double DEFAULT_HEADING = 90; //this is straight up, and when incremented rotates counter clockwise (to work with math.radians)

  protected double xPos, yPos, heading;

  protected Coordinates(){
    this.xPos = DEFAULT_X;
    this.yPos = DEFAULT_Y;
    this.heading = DEFAULT_HEADING;
    setValue(this);
  }
  protected Coordinates(double x, double y, double heading){
    this.xPos = x;
    this.yPos = y;
    this.heading = heading;
    setValue(this);
  }

  /**
   * Gets the x-coordinate of the object.
   *
   * @return The current x-coordinate of the object
   */
  public abstract double getX();

  /**
   * Sets the x-coordinate of the object.
   *
   * @param x The new x-coordinate of the object.
   */
  public abstract void setX(double x);

  /**
   * Gets the y-coordinate of the object.
   *
   * @return The current y-coordinate of the object
   */
  public abstract double getY();

  /**
   * Sets the y-coordinate of the object.
   *
   * @param y The new y-coordinate of the object
   */
  public abstract void setY(double y);

  /**
   * Gets the current heading of the object in degrees.
   *
   * @return heading The current heading of the object in degrees.
   */
  public abstract double getHeading();

  /**
   * Sets the heading of the object in degrees.
   *
   * @param heading The new heading of the object in degrees.
   */
  public abstract void setHeading(double heading);

  /**
   *
   * @return
   */
  public abstract DoubleProperty headValProperty();

  @Override
  public boolean equals(Object o) {
    if (o instanceof Coordinates) {
      Coordinates other = (Coordinates) o;
      return (getX() == other.getX()) && (getY() == other.getY());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int prime = 43;
    int ret = 1;
    ret += prime * prime * ((Double)(getX())).hashCode();
    ret += prime * ((Double)(getY())).hashCode();
    return ret;
  }

  @Override
  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }

}