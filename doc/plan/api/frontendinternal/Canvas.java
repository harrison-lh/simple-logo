/**
 * Holds the turtle and grid views, drawing pen lines and moving the turtle
 * around the canvas
 */
interface Canvas {

  /**
   * Changes the grid type (no grid, axis, gridlines, etc)
   * @param gridType What the grid type is set to
   */
  public void setGridType(String gridType);

  /**
   * Change the color of the background of the canvas
   * @param color New background color
   */
  public void setBackgroundColor(Color color);

  /**
   * Updates the turtle's location or heading or both
   * @param turtleProperties The properties of the turtle's updated location
   */
  public void moveTurtle(TurtleProperties turtleProperties);

  /**
   * Draws a line from the start coordinate to stop coordinate if the pen is down
   * @param start Start coordinate of the line
   * @param stop Stop coordinate of the line
   */
  public void drawPath(Coordinate start, Coordinate stop);
}