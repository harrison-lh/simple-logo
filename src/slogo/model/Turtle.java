package slogo.model;

import slogo.model.Coordinates;

/**
 * The Turtle is the object that commands of forward and right are put upon, and it contains
 * the fundamental variables of where the turtle is placed and facing.
 *
 * @author Harrison Huang
 * @author Cole Spector
 */
public class Turtle {

  private Coordinates coordinates;


  /**
   * Default constructor for Turtle.
   */
  public Turtle(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  /**
   * Creates a new Turtle object given parameters of a starting position and heading.
   *
   * @param x X coordinate
   * @param y Y coordinate
   * @param heading direction turtle is facing relative to positive x-axis
   */

  public Turtle(Coordinates coordinates, double x, double y, double heading) {
    this.coordinates = coordinates;
    coordinates.setX(x);
    coordinates.setY(y);
    coordinates.setHeading(heading);
  }

  /**
   * Moves the turtle forward a certain number of pixels (or backward, for a negative number).
   *
   * @param pixels number of pixels the turtle will move forward
   */
  public void forward(double pixels) {
    double xPos = coordinates.getX();
    double yPos = coordinates.getY();
    double heading = coordinates.getHeading();

    xPos += pixels * Math.cos(Math.toRadians(heading));
    yPos += pixels * Math.sin(Math.toRadians(heading));

    setX(xPos);
    setY(yPos);

  }

  /**
   * Turns the turtle to the right for a certain number of degrees.
   *
   * @param degrees number of degrees the turtle will move clockwise
   */
  public void right(double degrees) {

    double heading = coordinates.getHeading();

    setHeading(heading - degrees);
  }

  /**
   * Turns the turtle to the left for a certain number of degrees.
   *
   * @param degrees number of degrees the turtle will move counter-clockwise
   */
  public void left(double degrees) {

    double heading = coordinates.getHeading();

    setHeading(heading + degrees);
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
   * Setter method for the x-coordinate of the turtle.
   *
   * @param x The new x-coordinate of the object.
   */

  public void setX(double x) {
    coordinates.setX(x);
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
   * Setter method for the y-coordinate of the turtle.
   *
   * @param y The new y-coordinate of the object
   */

  public void setY(double y) {
     coordinates.setY(y);
  }

  /**
   * Getter method for obtaining the heading of the turtle
   * in degrees counterclockwise from the x-axis.
   *
   * @return heading of the turtle in degrees
   */

  public double getHeading() {
    return coordinates.getHeading();
  }

  /**
   * Setter method for the heading of the turtle. Automatically recalculates to a number
   * between 0 and 360 degrees.
   *
   * @param heading The new heading of the object in degrees.
   */

  public void setHeading(double heading) {
    coordinates.setHeading(heading);
  }
}
