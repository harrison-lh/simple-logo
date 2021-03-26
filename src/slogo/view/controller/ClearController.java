package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import slogo.view.LanguageConsumer;

/**
 * Clear screen control.
 *
 * @author David Li
 */
public class ClearController extends ControllerElement implements LanguageConsumer {

  private final Button myClearButton;

  /**
   * Main constructor
   */
  public ClearController() {
    super();
    this.setId("ClearController");
    myClearButton = new Button("Clear");
    myClearButton.setId("ControllerClearButton");
    this.getChildren().addAll(myClearButton);
  }

  /***
   * Sends the clear screen command to the consumer
   * @param response the consumer receiving commands
   */
  public void setExecuteCommandActions(Consumer<String> response) {
    myClearButton.setOnAction(e -> executeRawCommand(response, "ClearScreen"));
  }
}
