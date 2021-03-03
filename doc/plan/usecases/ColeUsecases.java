public class ColeUsecases.java {
    /**
     * Use Case: the user types "50 fd" as a command
     */
  public void 50fd(String commandString){
      CommandParser parser = new CommandParser();

      //This parser will parse the command and see an invalid regex format, and return the EXCEPTION command
      List<Command> commandList = parser.parseCommnands(commandString);


      Turtle turtle = new Turtle(); // Default constructor will have penDown, and isVisible by default
      for(Command command : commandList) {
        turtle.executeCommand(command); //This command will cause the user to be alerted of the error, and the console to be cleared
      }
      turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }

    /**
     * Use Case: the user types "fd fd 50" as a command
     */
  public void fdfd50(String commandString) {
      CommandParser parser = new CommandParser();

      // This will contain a semi-single command, that uses the forward function with a parameter forward, with the parameter 50
      List<Command> commandList = parser.parseCommnands(commandString);

      Turtle turtle = new Turtle(); // Default constructor will have penDown, and isVisible by default
      for(Command command : commandList) {
        // The innner forward function (the one used as a parameter) will be run first, and its return value (50)
        // will be used as the parameter for the outer forward function.
        turtle.executeCommand(command); // Execution of command is handled by the Turtle
      }
      turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
    }



}