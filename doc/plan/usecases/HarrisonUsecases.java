private class HarrisonUseCases {

  /**
   * The user sets a variable's value and sees it updated in the UI's Variable view.
   */
  public void changeVariable(int value, Variable variable) {
    variable.setValue(value);
    View.updateVariable(variable);
  }

  /**
   * The user updates a command and sees it updated in the view.
   */
  public void changeCommand(Command command) {
    View.updateUDCommand(command);
  }

  /**
   * The view is updated for all the turtles in the next step.
   */
  public void updateTurtles(TurtleArmy turtleArmy) {
    View.updateTurtleView(turtleArmy);
  }

  /**
   * An error has been detected and accordingly, a message needs to be sent to the view.
   */
  public void displayError(Error error) {
    View.throwError(error);
  }
}