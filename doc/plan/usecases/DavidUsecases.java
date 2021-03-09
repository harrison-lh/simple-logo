private class DavidUsecases {
  /**
   * The user sets the pen's color using the UI
   * so subsequent lines drawn when the turtle moves use that color.
   */
  public void changePenColor(Color color) {
    // Listener attached to JavaFXPen listens for new color selected in PenSelector
    JavaFXPen.changeColor(color);
    // Listener attached to Pen in the model listens for new color selected in PenSelector
    Pen.changeColor(color);
  }

  /**
   * The user changes the color of the background
   */
  public void changeBackgroundColor(Color color) {
    // Listener attached to Canvas listens for new color selected in BackgroundSelector
    Canvas.setBackgroundColor(color);
  }

  /**
   * The user changes the language
   */
  public void changeLanguage(String language) {
    // Listener attached to CommandParser listens for new language selected in LanguageSelector
    CommandParser.setLanguage(language);
  }

  /**
   * The user changes the turtle image
   */
  public void changeTurtleImage(ImageView image) {
    // Listener attached to TurtleView listens for new image selected in TurtleSelector
    TurtleView.setImage(image);
  }
}