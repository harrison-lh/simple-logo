import src.slogo.model.Command;
import src.slogo.model.TurtleArmy;
import src.slogo.model.TurtleInfantry;


/**
 * This is the interface for the backend external API, which functions to parse the commands given to it as a
 * String and to use those commands to augment all active turtles.
 */
public interface BackendExternalAPI {

  /**
   * This is the init for BackendExternalAPI, which creates an instance of BackEndTurtleAPI
   * around the passed TurtleArmy
   *
   * @param turtleArmy the TurtleArmy to make the BackendExternalAPI around
   */
  public BackendExternalAPI(TurtleArmy turtleArmy);

  /**
   * This method assigns a specific Command to a TurtleInfantry to execute.
   *
   * @param turtleToCommand the TurtleInfantry which will receive the Command
   * @param command the Command to be given to the TurtleInfantry
   */
  public void commandTurtle(TurtleInfantry turtleToCommand, Command command);

  /**
   * This method parses an input String and translates it into a Command.java, which
   * can be run on a Turtle.  If the String cannot be translated, it will return the
   * EXCEPTION Command
   *
   * @param command the string containing the Command to translate
   * @return the input string translated to its corresponding Command,
   * or the EXCEPTION Command the input String is not formatted correctly
   */
  public Command parseCommand(String command);

  /**
   * This methods iterates though all of the TurtleInfantry in the TurtleArmy, and
   * has them execute their next Command.
   */
  public void step();



}
