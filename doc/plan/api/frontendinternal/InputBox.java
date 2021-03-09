/**
 * Box in the window where the user inputs commands
 */
interface InputBox {

  /**
   * Retrieves everything typed in to the input box as a command
   * @return The command typed into the input box
   */
  public String getUserInput();
}