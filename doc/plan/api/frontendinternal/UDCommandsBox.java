/**
 * A display of the user-defined commands
 */
interface UDCommandsBox {

  /**
   * Add a UD command to the UD commands box
   * @param command UD command to be added
   */
  public void addVariable(Command command);

  /**
   * Update a UD command to the UD commands box
   * @param command UD command to be updated
   */
  public void updateVariable(Command command);

  /**
   * Remove a UD command to the UD commands box
   * @param command UD command to be removed
   */
  public void removeVariable(Command command;
}