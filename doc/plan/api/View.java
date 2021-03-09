/**
 * The externally facing interface for the frontend view. The view contains the current turtles,
 * the current list of variables, and the current list of user-defined commands, all to be displayed
 * back to the user. These view elements are all able to be updated by the API, along with
 * error messages to be displayed.
 *
 * The input text for determining the next command can also be outputted.
 *
 * @author Harrison Huang
 */
public interface View {

  /**
   * Updates the view of all turtles on screen.
   *
   * @param turtleArmy The TurtleArmy object containing all active turtles
   */
  public void updateTurtleView(TurtleArmy turtleArmy);

  /**
   * Adds a variable to be displayed on screen.
   *
   * @param var The variable to be added
   */
  public void addVariable(Variable variable);

  /**
   * Updates an existing variable displayed on screen.
   *
   * @param var The existing variable to be updated
   */
  public void updateVariable(Variable variable);

  /**
   * Deletes an existing variable displayed on screen.
   *
   * @param var The existing variable to be deleted
   */
  public void removeVariable(Variable variable);

  /**
   * Adds a user-defined command to be displayed on screen.
   *
   * @param command The command to be added
   */
  public void addUDCommand(Command command);

  /**
   * Modifies the text of an existing user-defined command displayed on screen.
   *
   * @param command The command to be updated
   */
  public void updateUDCommand(Command command);

  /**
   * Removes an existing user-defined command displayed on screen.
   *
   * @param command The command to be deleted
   */
  public void removeUDCommand(Command command);

  /**
   * Gets the contents of the user input box for the next command to be run.
   *
   * @return the user's input for the next command as a String
   */
  public String getUserInput();

  /**
   * Handles errors thrown by the controller by displaying a message on screen.
   *
   * @param error The error to be displayed
   */
  public void throwError(Error error);
}