package slogo.view.info;

import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import slogo.view.ClickableEntry;

/**
 * Entry in the user-defined commands display
 *
 * @author David Li
 */
public class UserCommandEntry extends ClickableEntry<String> {

  private final String myCommand;

  /**
   * Main constructor
   * @param command Name of the user command
   * @param consumer
   */
  public UserCommandEntry(String command, Consumer<String> consumer) {
    super(command, consumer);
    myCommand = command;
  }

  /**
   * Opens up input area for parameters and executes command
   * @param consumer Consumes the command
   */
  @Override
  protected void onClick(Consumer<String> consumer) {
    // TODO: figure out how to pass parameters
    consumer.accept(myCommand);
  }
}
