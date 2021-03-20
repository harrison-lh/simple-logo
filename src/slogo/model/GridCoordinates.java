package slogo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GridCoordinates extends Coordinates {

  private final StringProperty positionString;

  public GridCoordinates(double x, double y, double heading) {
    super(x, y, heading);
    positionString = new SimpleStringProperty(toString());
  }

  public GridCoordinates() {
    this(DEFAULT_X, DEFAULT_Y, DEFAULT_HEADING);
  }

  public GridCoordinates(Coordinates coordinates) {
    this(coordinates.getX(), coordinates.getY(), coordinates.getHeading());
  }

  @Override
  public double getX() {
    return xPos;
  }

  @Override
  public void setXY(double x, double y) {
    xPos = x;
    yPos = y;
    updateStringProperty();
  }

  @Override
  public double getY() {
    return yPos;
  }

  @Override
  public double getHeading() {
    return heading;
  }

  @Override
  public void setHeading(double heading) {
    if (heading > MAX_DEGREES || heading < 0) {
      heading = heading % MAX_DEGREES;
      if (heading < 0) heading += MAX_DEGREES;
    }
    this.heading = heading;
    updateStringProperty();
  }

  @Override
  public StringProperty stringProperty() {
    return positionString;
  }

  private void updateStringProperty() {
    positionString.set(toString());
  }

}
