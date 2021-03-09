package slogo.model;

/**
 * An interface that defines the properties of an object that has Coordinates.
 * <p>
 * Coordinates allow the View to determine where a Turtle or other Model object is in space, as well
 * as its heading and vice versa.
 *
 * @author Marc Chmielewski
 */
public interface Coordinates {

  /**
   * Gets the x-coordinate of the object.
   *
   * @return The current x-coordinate of the object
   */
  public double getX();

  /**
   * Sets the x-coordinate of the object.
   *
   * @param x The new x-coordinate of the object.
   */
  public void setX(double x);

  /**
   * Gets the y-coordinate of the object.
   *
   * @return The current y-coordinate of the object
   */
  public double getY();

  /**
   * Sets the y-coordinate of the object.
   *
   * @param y The new y-coordinate of the object
   */
  public void setY(double y);

  /**
   * Gets the current heading of the object in degrees.
   *
   * @return heading The current heading of the object in degrees.
   */
  public double getHeading();

  /**
   * Sets the heading of the object in degrees.
   *
   * @param heading The new heading of the object in degrees.
   */
  public void setHeading(double heading);
}