/**
 * The turtle image that appears on the canvas
 */
interface TurtleView {

  /**
   * Change the turtle image
   * @param image New turtle image
   */
  public void setTurtleImage(Image image);

  /**
   * Change the heading/direction/orientation of the turtle
   * @param heading New heading
   */
  public void setHeading(double heading);
}