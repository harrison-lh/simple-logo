package slogo.view.info;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import slogo.view.ClickableEntry;

public class UserCommandEntry extends ClickableEntry<String> {

  private final String myCommand;

  public UserCommandEntry(String command, Consumer<String> consumer) {
    super(command, consumer);
    myCommand = command;
  }

  // TODO: figure out how to pass parameters
  @Override
  protected void onClick(Consumer<String> consumer) {
    consumer.accept(myCommand);
  }
}
