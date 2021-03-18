package slogo.view;

import java.util.function.Consumer;

public class CommandHistoryEntry extends ClickableEntry<String> {

  private final String myCommand;

  public CommandHistoryEntry(String command, Consumer<String> consumer) {
    super(consumer);
    myCommand = command;
    this.setText(reformatCommand(command));
  }

  @Override
  protected void onClick(Consumer<String> consumer) {
    consumer.accept(myCommand);
  }

  private String reformatCommand(String command) {
    return "> " + command.replace("\n", "\n  ");
  }
}
