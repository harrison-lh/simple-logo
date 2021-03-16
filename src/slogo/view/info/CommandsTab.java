package slogo.view.info;

public class CommandsTab extends InfoTab {

  private final CommandsBox myCommandsBox;

  public CommandsTab() {
    super("Commands");
    myCommandsBox = new CommandsBox();
    this.setContent(myCommandsBox);
  }

  public CommandsBox getCommandsBox() {
    return myCommandsBox;
  }
}
