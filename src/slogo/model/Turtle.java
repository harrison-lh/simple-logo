package slogo.model;

/**
 *
 */
public class Turtle implements Coordinates{

  private double xPos;
  private double yPos;
  private double heading;
  private static double DEFAULT_X = 0;
  private static double DEFAULT_Y = 0;
  private static double DEFAULT_HEADING = 90;
  private static double MAX_DEGREES = 360;

  /**
   *
   */
  public Turtle() {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_HEADING);
  }

  /**
   *
   * @param x
   * @param y
   * @param heading
   */
  public Turtle(double x, double y, double heading) {
    xPos = x;
    yPos = y;
    this.heading = heading % MAX_DEGREES;
  }

  /**
   *
   * @param pixels
   */
  public void forward(double pixels) {

  }

  /**
   *
   * @param degrees
   */
  public void right(double degrees) {

  }

  @Override
  public double getX() {
    return 0;
  }

  @Override
  public void setX(double x) {

  }

  @Override
  public double getY() {
    return 0;
  }

  @Override
  public void setY(double y) {

  }

  @Override
  public double getHeading() {
    return 0;
  }

  @Override
  public void setHeading(double heading) {

  }
}
