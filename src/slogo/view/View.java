package slogo.view;

import slogo.controller.Command;
import slogo.model.Turtle;
import slogo.model.Variables;

/**
 * The externally facing interface for the frontend view. The view contains the current turtles,
 * the current list of variables, and the current list of user-defined commands, all to be displayed
 * back to the user. These view elements are all able to be updated by the API, along with
 * error messages to be displayed.
 *
 * The input text for determining the next command can also be outputted.
 *
 * @author Harrison Huang
 * @author David Li
 */
interface View {

  /**
   * Updates the view of all turtles on screen.
   *
   * @param turtle The Turtle object containing active turtle
   */
  void updateTurtleView(Turtle turtle);

  /**
   * Adds a variable to be displayed on screen.
   *
   * @param variables The variable to be added
   */
  void addVariable(Variables variables);

  /**
   * Updates an existing variable displayed on screen.
   *
   * @param variables The existing variable to be updated
   */
  void updateVariable(Variables variables);

  /**
   * Deletes an existing variable displayed on screen.
   *
   * @param variables The existing variable to be deleted
   */
  void removeVariable(Variables variables);

  /**
   * Adds a user-defined command to be displayed on screen.
   *
   * @param command The command to be added
   */
  void addUDCommand(Command command);

  /**
   * Modifies the text of an existing user-defined command displayed on screen.
   *
   * @param command The command to be updated
   */
  void updateUDCommand(Command command);

  /**
   * Removes an existing user-defined command displayed on screen.
   *
   * @param command The command to be deleted
   */
  void removeUDCommand(Command command);

  /**
   * Gets the contents of the user input box for the next command to be run.
   *
   * @return the user's input for the next command as a String
   */
  String getUserInput();

  /**
   * Handles errors thrown by the controller by displaying a message on screen.
   *
   * @param error The error to be displayed
   */
   void throwError(Error error);
}