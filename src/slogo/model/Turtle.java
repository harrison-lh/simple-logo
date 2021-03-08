package slogo.model;

/**
 * The Turtle is the object that commands of forward and right are put upon, and it contains
 * the fundamental variables of where the turtle is placed and facing.
 *
 * @author Harrison Huang
 */
public class Turtle implements Coordinates{

  private double xPos;
  private double yPos;
  private double heading;
  private static final double DEFAULT_X = 0;
  private static final double DEFAULT_Y = 0;
  private static final double DEFAULT_HEADING = 90;
  private static final double MAX_DEGREES = 360;

  /**
   * Default constructor for Turtle.
   */
  public Turtle() {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_HEADING);
  }

  /**
   * Creates a new Turtle object given parameters of a starting position and heading.
   *
   * @param x X coordinate
   * @param y Y coordinate
   * @param heading direction turtle is facing relative to positive x-axis
   */
  public Turtle(double x, double y, double heading) {
    xPos = x;
    yPos = y;
    this.heading = heading % MAX_DEGREES;
  }

  /**
   * Moves the turtle forward a certain number of pixels (or backward, for a negative number).
   *
   * @param pixels number of pixels the turtle will move forward
   */
  public void forward(double pixels) {
    xPos += pixels * Math.cos(Math.toRadians(heading));
    yPos += pixels * Math.sin(Math.toRadians(heading));
  }

  /**
   * Turns the turtle to the right for a certain number of degrees.
   *
   * @param degrees number of degrees the turtle will move clockwise
   */
  public void right(double degrees) {
    setHeading(heading - degrees);
  }

  /**
   *
   * @return
   */
  @Override
  public double getX() {
    return xPos;
  }

  /**
   *
   * @param x The new x-coordinate of the object.
   */
  @Override
  public void setX(double x) {
    xPos = x;
  }

  /**
   *
   * @return
   */
  @Override
  public double getY() {
    return yPos;
  }

  /**
   *
   * @param y The new y-coordinate of the object
   */
  @Override
  public void setY(double y) {
    yPos = y;
  }

  /**
   *
   * @return
   */
  @Override
  public double getHeading() {
    return heading;
  }

  /**
   *
   * @param heading The new heading of the object in degrees.
   */
  @Override
  public void setHeading(double heading) {
    if (heading % MAX_DEGREES >= 0) {
      this.heading = heading % MAX_DEGREES;
    }
    else this.heading = (heading % MAX_DEGREES) + MAX_DEGREES;
  }
}
