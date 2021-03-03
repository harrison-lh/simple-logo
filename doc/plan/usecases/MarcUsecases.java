private class MarcUsecases {

  /**
   * The user types 'fd 50' in the command window, and sees the turtle move in the display window
   * leaving a trail, and the command is added to the environment's history.
   */
  public void fd50(String commandString) {
    CommandParser parser = new CommandParser();

    // This will contain a single command, that uses the forward function with parameter 50
    List<Command> commandList = parser.parseCommnands(commandString);

    Turtle turtle = new Turtle(); // Default constructor will have penDown, and isVisible by default
    for(Command command : commandList) {
      turtle.executeCommand(command); // Execution of command is handled by the Turtle
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }
}