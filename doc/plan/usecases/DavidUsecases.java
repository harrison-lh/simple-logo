private class DavidUsecases {
  /**
   * The user sets the pen's color using the UI
   * so subsequent lines drawn when the turtle moves use that color.
   */
  public void changePenColor(Color color) {
    // Listener attached to JavaFXPen listens for new color selected in PenSelector
    JavaFXPen.changeColor(color)
    // Listener attached to Pen in the model listens for new color selected in PenSelector
    Pen.changeColor(color)
  }
}