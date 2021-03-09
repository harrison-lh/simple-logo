package slogo.model;

public class GridCoordinates extends Coordinates {

  public GridCoordinates(double x, double y, double heading){
    super(x, y, heading);
  }

  public GridCoordinates(){
    super(DEFAULT_X, DEFAULT_Y, DEFAULT_HEADING);
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

    if(heading > MAX_DEGREES){
      heading = heading % MAX_DEGREES;
    }

    if(heading < 0){
      while(heading < 0){
        heading += MAX_DEGREES;
      }
    }

    this.heading = heading;

  }
}
