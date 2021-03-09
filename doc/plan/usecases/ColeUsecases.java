public class ColeUsecases.java {
    /**
     * Use Case: the user types "50 fd" as a command, the console clears and the user is presented with an error message
     */
  public void 50fd(String commandString){
      CommandParser parser = new CommandParser();

      //This parser will parse the command and see an invalid regex format, and return the EXCEPTION command
      List<Command> commandList = parser.parseCommnands(commandString);


      TurtleInfantry turtle = new TurtleInfantry(); // Default constructor will have penDown, and isVisible by default
      for(Command command : commandList) {
        //This command will cause the user to be alerted of the error, and the console to be cleared
        turtle.executeCommand(command);
      }
      turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }

    /**
     * Use Case: the user types "fd fd 50" as a command, and sees the turtle move in the display window
     * leaving a trail, and the command is added to the environment's history.
     */
  public void fdfd50(String commandString) {
    CommandParser parser = new CommandParser();

    // This will contain a semi-single command, that uses the forward function with a parameter forward, with the parameter 50
    List<Command> commandList = parser.parseCommnands(commandString);

    TurtleInfantry turtle = new TurtleInfantry(); // Default constructor will have penDown, and isVisible by default
    for(Command command : commandList) {
      // The innner forward function (the one used as a parameter) will be run first, and its return value (50)
      // will be used as the parameter for the outer forward function.
      turtle.executeCommand(command); // Execution of command is handled by the Turtle
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }

    /**
     * Use Case: the user types "MAKE :var 5" as a command, and sees "var = 5" added to the variable window
     */
  public void makevar5(String commandString) {
    CommandParser parser = new CommandParser();

    // This will contain a single command, that uses the make command with String index = var, and int value = 5;
    List<Command> commandList = parser.parseCommnands(commandString);

    TurtleInfantry turtle = new TurtleInfantry(); // Default constructor will have penDown, and isVisible by default
    for(Command command : commandList) {
      // IntegerVariable.add(index = "var", value = 5) will be called from within the turtle, most likely from within a
      // private makeVariable() method.
      // From within this, View.addVariable(IntegerVariable.get("var")) will be called
      turtle.executeCommand(command); // Execution of command is handled by the Turtle
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }

    /**
     * Use Case: the user types "XCOR" as a command, and is shows the turtles x-coordinate in the command history
     * "\tXCOR = <int>"
     */
  public void xcor(String commandString) {
    CommandParser parser = new CommandParser(); ""

    // This will contain a single command, which will print "\tXCOR = <int>" in the command history tab
    List<Command> commandList = parser.parseCommnands(commandString);

    TurtleInfantry turtle = new TurtleInfantry(); // Default constructor will have penDown, and isVisible by default
    for(Command command : commandList) {
    // From within this method, a private getXCoordinate() method will be called, which will call Turtle.getCoordinates() (one the Turtle
    // housed within the specific TurtleInfantry), and then Coordinate.getX() will be called.  The output of that will be
    // printed in the command history as "\tXCOR = <Coordinate.getX()>".
    turtle.executeCommand(command); // Execution of command is handled by the Turtle
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
    }

}