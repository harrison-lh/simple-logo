package slogo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class GridCoordinates extends Coordinates {

  private DoubleProperty xVal;
  private DoubleProperty yVal;
  private DoubleProperty headVal;

  public GridCoordinates(double x, double y, double heading) {
    super(x, y, heading);
    xVal = new SimpleDoubleProperty(x);
    yVal = new SimpleDoubleProperty(y);
    headVal = new SimpleDoubleProperty(heading);
  }

  public GridCoordinates() {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_HEADING);
  }

  public GridCoordinates(Coordinates coordinates) {
    this(coordinates.getX(), coordinates.getY(), coordinates.getHeading());
  }

  @Override
  public double getX() {
    return xVal.get();
  }

  @Override
  public void setX(double x) {
    xVal.set(x);
    setValue(this);
  }

  public DoubleProperty xValProperty() {
    return xVal;
  }

  @Override
  public double getY() {
    return yVal.get();
  }

  @Override
  public void setY(double y) {
    yVal.set(y);
    setValue(this);
  }

  public DoubleProperty yValProperty() {
    return yVal;
  }

  @Override
  public double getHeading() {
    return headVal.get();
  }

  @Override
  public void setHeading(double heading) {
    if (heading > MAX_DEGREES || heading < 0) {
      heading = heading % MAX_DEGREES;
      if (heading < 0) heading += MAX_DEGREES;
    }
    headVal.set(heading);
  }

  @Override
  public DoubleProperty headValProperty() {
    return headVal;
  }

}
