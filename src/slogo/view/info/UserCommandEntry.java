package slogo.view.info;

import java.util.List;
import java.util.function.Consumer;
import slogo.view.ClickableEntry;

/**
 * Entry in the user-defined commands display
 *
 * @author David Li
 */
public class UserCommandEntry extends ClickableEntry<String> {

  private String myCommand;
  private List<String> myParameters;

  /**
   * Main constructor
   *
   * @param command    Name of the user command
   * @param parameters List of parameters of the command
   * @param consumer  Execute command consumer
   */
  public UserCommandEntry(String command, List<String> parameters,
      Consumer<String> consumer) {
    super(command, consumer);
    myCommand = command;
    myParameters = parameters;
    setText(displayText());
  }

  /**
   * @param command New command name
   * @param parameters New list of parameters
   */
  public void updateCommand(String command, List<String> parameters) {
    myCommand = command;
    myParameters = parameters;
    setText(displayText());
  }

  /**
   * Executes command with no parameters
   *
   * @param consumer Consumes the command
   */
  @Override
  protected void onClick(Consumer<String> consumer) {
    // TODO: figure out how to pass parameters
    consumer.accept(myCommand);
  }

  private String displayText() {
    String text = myCommand + " [ ";
    for (String param : myParameters) {
      text += param.substring(1) + " ";
    }
    text += " ]";
    return text;
  }

}
