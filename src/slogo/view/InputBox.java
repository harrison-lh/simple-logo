package slogo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 * Input box where the user types in commands and executes them using the input button
 *
 * @author David Li
 */
public class InputBox extends BorderPane {

  public static final double BOTTOM_HEIGHT = 180;
  private static final double WIDTH = 360;
  private final TextArea myInputBoxArea;
  private final Button myInputButton;

  /**
   * Main constructor
   */
  public InputBox() {
    this.setId("InputBox");

    this.setPrefSize(WIDTH, BOTTOM_HEIGHT);

    myInputBoxArea = new TextArea();
    myInputBoxArea.setId("InputBoxArea");
    myInputBoxArea.setPrefHeight(BOTTOM_HEIGHT);
    myInputBoxArea.setOnKeyPressed(this::setInputShortcut);

    myInputButton = new Button("Go");
    myInputButton.setId("InputButton");
    myInputButton.prefWidthProperty().bind(this.widthProperty());
    this.setTop(myInputBoxArea);
    this.setBottom(myInputButton);
  }

  /**
   * Sets the event that occurs when the input button is clicked
   *
   * @param event
   */
  public void setInputAction(EventHandler<ActionEvent> event) {
    myInputButton.setOnAction(event);
  }

  /**
   * @return Text typed into the input box by the user
   */
  public String getText() {
    return myInputBoxArea.getText();
  }

  /**
   * Clears all text written in the input box
   */
  public void clear() {
    myInputBoxArea.clear();
  }

  private void setInputShortcut(KeyEvent keyEvent) {
    if (keyEvent.isShortcutDown() && keyEvent.getCode() == KeyCode.ENTER) {
      myInputButton.fire();
    }
  }
}
