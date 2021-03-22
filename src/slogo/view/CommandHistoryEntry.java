package slogo.view;

import java.util.function.Consumer;

/**
 * Entry in command history box
 *
 * @author David Li
 */
public class CommandHistoryEntry extends ClickableEntry<String> {

  private final String myCommand;

  /**
   * Main constructor
   *
   * @param command  Command to be displayed/executed when clicked
   * @param consumer Consumer of the re-executed command
   */
  public CommandHistoryEntry(String command, Consumer<String> consumer) {
    super(consumer);
    myCommand = command;
    this.setText(reformatCommand(command));
    this.getLabel().getStyleClass().add("monospace-font");
  }

  @Override
  protected void onClick(Consumer<String> consumer) {
    consumer.accept(myCommand);
  }

  private String reformatCommand(String command) {
    return "> " + command.replace("\n", "\n  ");
  }
}
