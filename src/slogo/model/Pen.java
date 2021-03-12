package slogo.model;

public abstract class Pen {

  protected boolean isActive = true;

  public abstract void stamp(double x, double y, double heading);

  public void liftPen(){
    isActive = false;
  }

  public void placePen(){
    isActive = true;
  }

  public boolean isPenActive(){
    return isActive;
  }
}
