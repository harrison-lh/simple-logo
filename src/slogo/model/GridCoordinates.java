package slogo.model;

public class GridCoordinates implements Coordinates {

  private double xPos, yPos, heading;

  public GridCoordinates(double x, double y, double heading){
    this.xPos = x;
    this.yPos = y;
    this.heading = heading;
  }

  @Override
  public double getX() {
    return xPos;
  }

  @Override
  public void setX(double x) {
    xPos = x;
  }

  @Override
  public double getY() {
    return yPos;
  }

  @Override
  public void setY(double y) {
    yPos = y;
  }

  @Override
  public double getHeading() {
    return heading;
  }

  @Override
  public void setHeading(double heading) {
    this.heading = heading;
  }
}
