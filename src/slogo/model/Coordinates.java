package slogo.model;

import java.text.DecimalFormat;
import javafx.beans.property.StringProperty;

/**
 * An interface that defines the properties of an object that has Coordinates.
 * <p>
 * Coordinates allow the View to determine where a Turtle or other Model object is in space, as well
 * as its heading and vice versa.
 *
 * @author Marc Chmielewski
 * @author Cole Spector
 */
public abstract class Coordinates {

  protected static final double MAX_DEGREES = 360;
  protected static final double DEFAULT_X = 0;
  protected static final double DEFAULT_Y = 0;
  protected static final double DEFAULT_HEADING = 90; //this is straight up, and when incremented rotates counter clockwise (to work with math.radians)

  protected double xPos, yPos, heading;

  protected Coordinates() {
    this.xPos = DEFAULT_X;
    this.yPos = DEFAULT_Y;
    this.heading = DEFAULT_HEADING;
  }

  protected Coordinates(double x, double y, double heading) {
    this.xPos = x;
    this.yPos = y;
    this.heading = heading;
  }

  /**
   * Gets the x-coordinate of the object.
   *
   * @return The current x-coordinate of the object
   */
  public abstract double getX();

  /**
   * Gets the y-coordinate of the object.
   *
   * @return The current y-coordinate of the object
   */
  public abstract double getY();

  /**
   * Sets the x- and y-coordinates of the object.
   *
   * @param x The new x-coordinate of the object
   * @param y The new y-coordinate of the object
   */
  public abstract void setXY(double x, double y);

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

  public abstract StringProperty stringProperty();

  @Override
  public String toString() {
    return "(" + round(getX()) + ", " + round(getY()) + "), " + round(getHeading()) + " degrees";
  }

  private String round(double x) {
    DecimalFormat df = new DecimalFormat("####.##");
    return df.format(x);
  }

}